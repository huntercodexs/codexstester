package com.huntercodexs.codexstester.dto;

import java.util.Map;

public class HeadersDto {
    private String contentType;
    private String acceptable;
    private String httpMethod;
    private String statusCode;
    private String crossOrigin;
    private String origin;
    private String hostname;
    private String ip;
    private String osName;
    private String authorizationBasic;
    private String authorizationBearer;
    private String apiKeyToken;
    private String apiKeyAppName;
    private String apiKeySecret;
    private String apiKeyValue;
    private String apiKeyGeneric;
    private String additionalName;
    private String additionalValue;
    private Class<?> objectResponse;
    private Map<String,String> bodyParameters;

    public HeadersDto() {
    }

    public HeadersDto(String contentType, String acceptable, String httpMethod, String statusCode, String crossOrigin, String origin, String hostname, String ip, String osName, String authorizationBasic, String authorizationBearer, String apiKeyToken, String apiKeyAppName, String apiKeySecret, String apiKeyValue, String apiKeyGeneric, String additionalName, String additionalValue, Map<String, String> bodyParameters) {
        this.contentType = contentType;
        this.acceptable = acceptable;
        this.httpMethod = httpMethod;
        this.statusCode = statusCode;
        this.crossOrigin = crossOrigin;
        this.origin = origin;
        this.hostname = hostname;
        this.ip = ip;
        this.osName = osName;
        this.authorizationBasic = authorizationBasic;
        this.authorizationBearer = authorizationBearer;
        this.apiKeyToken = apiKeyToken;
        this.apiKeyAppName = apiKeyAppName;
        this.apiKeySecret = apiKeySecret;
        this.apiKeyValue = apiKeyValue;
        this.apiKeyGeneric = apiKeyGeneric;
        this.additionalName = additionalName;
        this.additionalValue = additionalValue;
        this.bodyParameters = bodyParameters;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAcceptable() {
        return acceptable;
    }

    public void setAcceptable(String acceptable) {
        this.acceptable = acceptable;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCrossOrigin() {
        return crossOrigin;
    }

    public void setCrossOrigin(String crossOrigin) {
        this.crossOrigin = crossOrigin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getAuthorizationBasic() {
        return authorizationBasic;
    }

    public void setAuthorizationBasic(String authorizationBasic) {
        this.authorizationBasic = authorizationBasic;
    }

    public String getAuthorizationBearer() {
        return authorizationBearer;
    }

    public void setAuthorizationBearer(String authorizationBearer) {
        this.authorizationBearer = authorizationBearer;
    }

    public String getApiKeyToken() {
        return apiKeyToken;
    }

    public void setApiKeyToken(String apiKeyToken) {
        this.apiKeyToken = apiKeyToken;
    }

    public String getApiKeyAppName() {
        return apiKeyAppName;
    }

    public void setApiKeyAppName(String apiKeyAppName) {
        this.apiKeyAppName = apiKeyAppName;
    }

    public String getApiKeySecret() {
        return apiKeySecret;
    }

    public void setApiKeySecret(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret;
    }

    public String getApiKeyValue() {
        return apiKeyValue;
    }

    public void setApiKeyValue(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    public String getApiKeyGeneric() {
        return apiKeyGeneric;
    }

    public void setApiKeyGeneric(String apiKeyGeneric) {
        this.apiKeyGeneric = apiKeyGeneric;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }

    public Class<?> getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Class<?> objectResponse) {
        this.objectResponse = objectResponse;
    }

    public Map<String, String> getBodyParameters() {
        return bodyParameters;
    }

    public void setBodyParameters(Map<String, String> bodyParameters) {
        this.bodyParameters = bodyParameters;
    }

    public String toString() {
        return "HeadersDto(" +
                "contentType=" + contentType +
                ", accepted=" + acceptable +
                ", httpMethod=" + httpMethod +
                ", statusCode=" + statusCode +
                ", crossOrigin=" + crossOrigin +
                ", origin=" + origin +
                ", hostname=" + hostname +
                ", ip=" + ip +
                ", osName=" + osName +
                ", authorizationBasic=" + authorizationBasic +
                ", authorizationBearer=" + authorizationBearer +
                ", apiKeyToken=" + apiKeyToken +
                ", apiKeyAppName=" + apiKeyAppName +
                ", apiKeySecret=" + apiKeySecret +
                ", apiKeyValue=" + apiKeyValue +
                ", apiKeyGeneric=" + apiKeyGeneric +
                ", additionalName=" + additionalName +
                ", additionalValue=" + additionalValue +
                ", objectResponse=" + objectResponse +
                ", bodyParameters=" + bodyParameters +
                ")";
    }
}
