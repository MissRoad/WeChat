package com.bing.dto.enumutil;

/**
 * Created by hurl on 2018-09-16
 */
public enum ResponseMessage {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
