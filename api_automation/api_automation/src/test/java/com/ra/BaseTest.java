package com.ra;

import com.ra.builder.Request;
import com.ra.builder.RequestBuilder;
import com.ra.constants.Method;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

@Epic("Api Test")
public class BaseTest {
    public Properties properties;

    @BeforeSuite
    public void setUp() throws IOException {
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("config/Config.properties");
        properties.load(inputStream);
    }
}
