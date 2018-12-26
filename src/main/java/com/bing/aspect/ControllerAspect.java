package com.bing.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP
 *访问接口 打印日志
 * @author
 * @create 2017-10-12 13:15
 */
@Aspect
@Component
public class ControllerAspect {

  private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

  @Pointcut("execution(public * com.bing.controller.*.*(..))")
  public void log() {
  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    //url
    logger.info("url={}", request.getRequestURL());
    //method
    logger.info("method={}", request.getMethod());
    //ip
    logger.info("info={}", request.getRemoteAddr());
    //类方法
    logger.info("class_method={}",
        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    //参数
    logger.info("args={}", joinPoint.getArgs());
  }

  @After("log()")
  public void doAfter() {
    logger.info("222");
  }

  @AfterReturning(returning = "object", pointcut = "log()")

  /**
   * @desc 方法返回的结果
   * @author 毛尚俊
   * @email 54298415@qq.com
   * @create 2017/10/13 16:45
   **/
  public void doAfterReturning(Object object) {
    logger.info("response={}", object);
  }
}

