package com.bing.authorization.interceptor;


import com.bing.authorization.annotation.Authorization;
import com.bing.authorization.annotation.Clear;
import com.bing.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自定义拦截器，对请求进行身份验证
 *
 * @author ScienJus
 * @date 2015/7/30.
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Resource
    WxMpService wxMpService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断请求的Controller类或方法是否有注解
        boolean authorizationMethod = method.getAnnotation(Authorization.class) != null;
        boolean clearMethod = method.getAnnotation(Clear.class) != null;
        boolean authorizationClazz = handlerMethod.getBeanType().getAnnotation(
                Authorization.class) != null;

        if (!(authorizationMethod || authorizationClazz) || clearMethod) {
            return true;
        }
        HttpSession session = request.getSession();
        WxMpUser wxMpUser = (WxMpUser) session.getAttribute(Constant.USER);
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            return false;
        }
        if (Objects.isNull(wxMpUser)) {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
            session.setAttribute(Constant.USER, wxMpUser);
        }
        return true;
    }

}
