package com.ra.builder;

import com.ra.constants.AuthType;
import com.ra.constants.Method;

import java.util.Map;

public class Request {
    String baseUri;
    String basePath;
    String endPoint;
    String requestBody;
    Map<String, String> queryParam;
    Map<String, String> pathParam;
    Map<String, String> headers;
    AuthType authType;
    Map<String, String> authParam;
    Method method;
    boolean logging;

    public Request(RequestBuilder requestBuilder) {
        this.baseUri = requestBuilder.baseUri;
        this.basePath = requestBuilder.basePath;
        this.endPoint = requestBuilder.endPoint;
        this.requestBody = requestBuilder.requestBody;
        this.queryParam = requestBuilder.queryParam;
        this.pathParam = requestBuilder.pathParam;
        this.headers = requestBuilder.headers;
        this.authType = requestBuilder.authType;
        this.method = requestBuilder.method;
        this.authParam = requestBuilder.authParam;
        this.logging = requestBuilder.logging;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public Map<String, String> getQueryParam() {
        return queryParam;
    }

    public Map<String, String> getPathParam() {
        return pathParam;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public Map<String, String> getAuthParam() {
        return authParam;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isLogging() {
        return logging;
    }
}
