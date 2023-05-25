package com.heyesinc.api.mindstamp.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Access {

    USER("USER"),
    ADMIN("ADMIN");

    private final String key;

    Access(String key){
        this.key = key;
    }

    @JsonValue
    public String getKey(){
        return key;
    }
}
