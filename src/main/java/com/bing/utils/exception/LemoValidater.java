package com.bing.utils.exception;


import com.bing.response.Result;

/**
 * @author 潘辉
 * @Description: 自定义异常抛出工具
 * @date 2017年4月9日 下午2:29:46
 */
public class LemoValidater {

  /**
   * @return void
   * @Description 判断是否为真，false抛出异常，通过异常拦截器返回指定code和msg
   * @author 潘辉
   * @date 2017年4月9日 下午2:15:29
   * @action isTrue
   */
  public static void isTrue(final boolean isTrue, final Result returnMsg) {
    if (!isTrue) {
      throw new MyException(returnMsg);
    }
  }

  /**
   * @return void
   * @Description 判断是否为真，false抛出异常，通过异常拦截器返回指定msg
   * @author 潘辉
   * @date 2017年4月9日 下午2:18:06
   * @action isTrue
   */
  public static void isTrue(final boolean isTrue, final String message) {
    if (!isTrue) {
      throw new MyException(message);
    }
  }

  /**
   * @return void
   * @Description 判断是否为空，为空抛出异常，通过异常拦截器返回指定msg
   * @author 潘辉
   * @date 2017年4月9日 下午2:19:04
   * @action notNull
   */
  public static void notNull(final Object object, final String message) {
    isTrue(object != null, message);
  }

  /**
   * @return void
   * @Description 判断是否为空，为空抛出异常，通过异常拦截器返回指定code和msg
   * @author 潘辉
   * @date 2017年4月9日 下午2:19:06
   * @action notNull
   */
  public static void notNull(final Object object, final Result result) {
    isTrue(object != null, result);
  }

}
