package com.bing.web;

import com.github.pagehelper.PageInfo;

/**
 * Created by hurl on 2018-09-16
 */
public class BaseResponse {
    private static final long serialVersionUID = 1518484851893182089L;
    private Boolean success = true;
    private ResourceError error;
    private String message;
    private Object result;
    private PageInfo page;

    public BaseResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ResourceError getError() {
        return error;
    }

    public void setError(ResourceError error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
