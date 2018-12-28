package com.bing.authorization.aspect;

import com.bing.authorization.annotation.WxUser;
import com.bing.constant.Constant;
import com.bing.response.ResultEnum;
import com.bing.utils.exception.MyException;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class WxUserAop {

    @Autowired
    WxMpService wxMpService;

    /**
     * Pointcut定义切点
     * public修饰符的   返回值任意包下面的任意类的任意方法任意参数
     */
    @Pointcut("execution(public * com.bing.controller.*.*(..))")
    public void getUser() {
    }

    @Before("getUser()")
    public void doBefore(JoinPoint point) throws NoSuchMethodException, WxErrorException {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = point.getTarget().getClass().getDeclaredMethod(signature.getName(),
                targetMethod.getParameterTypes());
        //当前类是否有注解
        if (realMethod.isAnnotationPresent(WxUser.class)) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            HttpSession session = request.getSession();
            WxMpUser wxMpUser = (WxMpUser) session.getAttribute(Constant.USER);
            String code = request.getParameter("code");
            if (StringUtils.isBlank(code)) {
                throw new MyException(ResultEnum.WX_ERROR);
            }
            if (Objects.isNull(wxMpUser)) {
                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
                wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
                session.setAttribute(Constant.USER, wxMpUser);
            }
        }
    }
}
