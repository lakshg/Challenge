package com.ra.core;

import com.ra.builder.Request;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.SkipException;

public class HttpOperation {

    Request request;

    public HttpOperation(Request request) {
        this.request = request;
    }

    public Response execute() {
        Response response;
        RequestSpecification requestSpecification = getRequestSpecification();
        requestSpecification.baseUri(request.getBaseUri());
        requestSpecification.basePath(request.getBasePath());
        requestSpecification.filter(new AllureRestAssured());

        if (!request.getQueryParam().isEmpty()) requestSpecification.queryParams(request.getQueryParam());
        if (!request.getPathParam().isEmpty()) requestSpecification.pathParams(request.getPathParam());
        if (request.isLogging()) requestSpecification.log().all();
        if(!request.getRequestBody().isEmpty()) requestSpecification.body(request.getRequestBody());

        response = switch (request.getMethod()) {
            case GET -> requestSpecification.get(request.getEndPoint());
            case POST ->
                    requestSpecification.contentType(ContentType.JSON).post(request.getEndPoint());
            case DELETE -> requestSpecification.delete(request.getEndPoint());
        };
        if (request.isLogging()) response.then().log().all();
        return response;
    }

    private RequestSpecification getRequestSpecification() {
        RequestSpecification requestSpecification = RestAssured.given();
        switch (request.getAuthType()) {
            case BASIC:
                requestSpecification.auth().basic(request.getAuthParam().get("username"), request.getAuthParam().get("password"));
                break;
            case NTLM:
                break;
            default:

        }
        return requestSpecification;
    }
}
