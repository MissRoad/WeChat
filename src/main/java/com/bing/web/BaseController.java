package com.bing.web;

import com.bing.dto.enumutil.ResponseMessage;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by hurl on 2018-09-16
 * 父类
 */
public abstract class BaseController {
    public static ResponseEntity<BaseResponse> buildSuccessResponse() {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setMessage(ResponseMessage.SUCCESS.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> buildFailResponse() {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage(ResponseMessage.ERROR.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> buildFailResponse(Object result) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setResult(result);
        response.setMessage(ResponseMessage.ERROR.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> buildSuccessResponse(Object result) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setResult(result);
        response.setMessage(ResponseMessage.SUCCESS.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> buildSuccessResponse(PageInfo page) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setResult(page);
        response.setMessage(ResponseMessage.SUCCESS.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> buildSuccessResponseWithPage(List<?> result) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setResult(new PageInfo(result));
        response.setMessage(ResponseMessage.SUCCESS.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
