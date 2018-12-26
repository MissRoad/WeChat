//package com.bing.authorization.interceptor;
//
//import com.bing.utils.redis.RedisService;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 登录拦截器
// *
// * @author fzq
// * @create 2017-12-19 16:48
// */
//public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
//    RedisService redisService;
//
//    /**
//     * @Description controller调用前
//     * @Author fzq
//     * @Date 2017/12/19 17:16
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        //        String result = (String) redisService.get("model");
//      /*  String result = (String) request.getSession().getAttribute("login");
//        if (StringUtils.isBlank(result)) {
//            response.sendRedirect("/student/index");
//            return false;
//        }
//        System.out.println("进入前");*/
//        return true;
//    }
//
//    /**
//     * @Description controller调用后
//     * @Author fzq
//     * @Date 2017/12/19 17:16
//     */
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("登录中");
//    }
//
//    /**
//     * @Description 页面渲染之后调用
//     * @Author fzq
//     * @Date 2017/12/19 17:17
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("登录后");
//    }
//}