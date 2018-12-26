package com.bing.utils.exception;


import com.bing.response.Result;
import com.bing.response.ResultEnum;

/**
 * @author 毛尚俊
 * @create 2017-10-12 15:52
 */
public class MyException extends RuntimeException {

  private Result result;


  public MyException() {
    result = new Result(ResultEnum.FAIL);
  }


  public MyException(String message) {
    result = new Result();
    result.setCode(Result.ERROR_CODE);
    result.setMsg(message);
  }

  public MyException(ResultEnum resultEnum) {
    result = new Result(resultEnum);
  }


  public Result getResult() {
    return result;
  }

  public MyException(Result result) {
    result = result;
  }
}

