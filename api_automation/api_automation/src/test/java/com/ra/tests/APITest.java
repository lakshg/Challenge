package com.ra.tests;

import com.ra.BaseTest;
import com.ra.builder.Request;
import com.ra.builder.RequestBuilder;
import com.ra.constants.Method;
import com.ra.core.HttpOperation;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
@Story("almosafer api test")
public class APITest extends BaseTest {
    @Test
    public void testListOfCity() {
        Request request = new RequestBuilder()
                .loadUsingConfig(properties)
                .setEndPoint("/lookup/city")
                .setMethod(Method.GET)
                .setLogging(true).getRequest();
        HttpOperation httpOperation = new HttpOperation(request);
        Response response = httpOperation.execute();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().assertThat().body("size()", is(10));
    }

    @Test
    public void listOfPropertyInPageOne() {
        Request request = new RequestBuilder()
                .loadUsingConfig(properties)
                .setEndPoint("/property/search")
                .setMethod(Method.POST)
                .setRequestBody("search.json")
                .setLogging(true).getRequest();
        HttpOperation httpOperation = new HttpOperation(request);
        Response response = httpOperation.execute();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().assertThat().body("properties.size()", is(10));
    }
}
