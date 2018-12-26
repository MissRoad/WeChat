/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************//*

package com.bing.utils.exception;


import com.bing.response.Result;
import com.bing.response.ResultEnum;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.web.MediaTypes;

*/
/**
 * 自定义ExceptionHandler，专门处理Restful异常.
 * 
 * @author calvin
 *//*

// 会被Spring-MVC自动扫描，但又不属于Controller的annotation。
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  private JsonMapper jsonMapper = new JsonMapper();

  */
/**
   * 
   * @Description 处理MyException.
   * @author 王兴岭
   * @date 2016年7月14日 下午1:05:40
   * @action handleException
   * @param ex
   * @param request
   * @return ResponseEntity<?>
   *//*

  @ExceptionHandler(value = {MyException.class})
  public final ResponseEntity<?> handleException(MyException ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
    Result result = ex.getResult();
    String body = jsonMapper.toJson(result);
    return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
  }

  @ExceptionHandler(value = {ServiceException.class})
  public final ResponseEntity<?> handleException(ServiceException ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
    String message = ex.getMessage();
    Result result = new Result(Result.MSG_CODE, message);
    String body = jsonMapper.toJson(result);
    return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
  }


  */
/**
   * 
   * @Description 处理RuntimeException
   * @author 王兴岭
   * @date 2016年7月14日 下午1:05:29
   * @action handleException
   * @param ex
   * @param request
   * @return ResponseEntity<?>
   *//*

  @ExceptionHandler(value = {RuntimeException.class})
  public final ResponseEntity<?> handleException(RuntimeException ex, WebRequest request) {
    String message = ex.getMessage();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
    Result result = new Result(Result.MSG_CODE, message);
    String body = jsonMapper.toJson(result);
    return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
  }

  @ExceptionHandler(value = {Exception.class})
  public final ResponseEntity<?> handleRestException(Exception ex, WebRequest request) {
    String message = ex.getMessage();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
    Result result = new Result(Result.MSG_CODE, message);
    String body = jsonMapper.toJson(result);
    return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
  }

  */
/**
   * 处理JSR311 Validation异常.
   *//*

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public final ResponseEntity<?> handleException(ConstraintViolationException ex,
      WebRequest request) {
    Map<String, String> errors =
        BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
    Result result = new Result(ResultEnum.FAIL);
    result.setData(errors);
    String body = jsonMapper.toJson(result);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
    return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
  }
}
*/
