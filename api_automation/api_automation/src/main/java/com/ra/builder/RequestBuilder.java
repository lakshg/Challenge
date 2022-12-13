package com.ra.builder;

import com.google.common.io.Resources;
import com.ra.constants.AuthType;
import com.ra.constants.Method;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RequestBuilder {
    String baseUri;
    String basePath;
    String endPoint;
    String requestBody = "";
    Map<String, String> queryParam = new HashMap<>();
    Map<String, String> pathParam = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    AuthType authType = AuthType.NO_AUTH;
    Map<String, String> authParam = new HashMap<>();
    Method method;
    boolean logging;

    public RequestBuilder loadUsingConfig(Properties properties) {
        if (properties.containsKey("baseUri")) setBaseUri(properties.getProperty("baseUri"));
        if (properties.containsKey("basePath")) setBasePath(properties.getProperty("basePath"));
        if (properties.containsKey("auth")) setAuthType(AuthType.getAuthUsingName(properties.getProperty("auth")));

        properties.keySet().stream()
                .map(key -> (String) key)
                .filter(key -> key.startsWith("header"))
                .map(key -> StringUtils.removeStart(key, "header."))
                .forEach(header ->updateQueryParam(header, properties.getProperty(header)));
        return this;
    }

    public RequestBuilder setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        return this;
    }

    public RequestBuilder setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public RequestBuilder setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public RequestBuilder setRequestBody(String fileName) {
        URL url = Resources.getResource(String.format("template/%s", fileName));
        try {
            this.requestBody = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public RequestBuilder setQueryParam(Map<String, String> queryParam) {
        this.queryParam = queryParam;
        return this;
    }

    public RequestBuilder updateQueryParam(String key, String value) {
        if (queryParam == null)
            queryParam = new HashMap<>();
        queryParam.put(key, value);
        return this;
    }

    public RequestBuilder setPathParam(Map<String, String> pathParam) {
        this.pathParam = pathParam;
        return this;
    }

    public RequestBuilder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestBuilder setAuthType(AuthType authType) {
        this.authType = authType;
        return this;
    }

    public RequestBuilder setMethod(Method method) {
        this.method = method;
        return this;
    }

    public RequestBuilder setLogging(boolean logging) {
        this.logging = logging;
        return this;
    }

    public Request getRequest() {
        return new Request(this);
    }
}
