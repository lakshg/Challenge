package com.ra.constants;

import org.apache.commons.lang3.EnumUtils;

public enum AuthType {
    BASIC("basic"),NTLM("ntlm"), NO_AUTH("noAuth");

    final String authType;

    public String getAuthType() {
        return authType;
    }

    AuthType(String authType) {
        this.authType=authType;
    }

    public static AuthType getAuthUsingName(String authName){
        return  EnumUtils.getEnumList(AuthType.class).stream().filter(auth->auth.getAuthType().equalsIgnoreCase(authName)).findFirst().orElse(null);
    }
}
