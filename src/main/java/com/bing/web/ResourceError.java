package com.bing.web;

/**
 * Created by hurl on 2018-09-16
 */
public class ResourceError {
    private int code;
    private String message;

    public ResourceError() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
