package com.bing.authorization.util;

import com.bing.constant.Constant;
import com.bing.utils.exception.ServiceException;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lemo on 2017/11/27.
 */
@Component
public class RedirectEntity {

  /**
   * @desc 用户同意授权，获取code
   * @author 曹留家
   * @create 2017/11/27 16:04
   **/
  public static String getRedirectUrl(String path, String url) {
    String redirectUrl = String.format("%s/author/index?redirect=%s", url, path);
    String redirect = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    try {
      return String.format(redirect, Constant.APP_ID, URLEncoder.encode(redirectUrl, "utf-8"));
    } catch (UnsupportedEncodingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
