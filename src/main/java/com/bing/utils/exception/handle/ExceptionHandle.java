package com.bing.utils.exception.handle;


import com.bing.utils.exception.MyException;
import com.bing.response.Result;
import com.bing.response.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截器
 *
 * @author 毛尚俊
 * @create 2017-10-12 15:37
 */
@ControllerAdvice
public class ExceptionHandle {

  private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Result handle(Exception e) {
    if (e instanceof MyException) {
      MyException myException = (MyException) e;
      logger.error("【code】{},【msg】{}，【message】{}",myException.getResult().getCode(), myException.getResult().getMsg(),myException.getMessage());
      return new Result(myException.getResult().getCode(), myException.getResult().getMsg());
    } else {
      logger.error("【系统异常】{}", e.getMessage());
      return new Result(ResultEnum.FAIL);
    }
  }
}

