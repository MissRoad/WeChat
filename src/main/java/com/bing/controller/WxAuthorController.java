//
//package com.bing.controller;
//
//import com.bing.authorization.user.TokenUser;
//import com.bing.authorization.util.JwtUtil;
//import com.bing.constant.Constant;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springside.modules.mapper.JsonMapper;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Enumeration;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * Created by lemo on 2017/11/27.
// */
//
//@RestController
//@RequestMapping("/author")
//public class WxAuthorController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping("/index")
//    public String getAuthor(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code") String code, @RequestParam(value = "redirect") String redirect, RedirectAttributes attr) {
//        Enumeration<String> enumeration = request.getHeaderNames();
//        StringBuffer params = new StringBuffer();
//        //redirectURL只会在后面拼一个参数，剩下的参数通过迭代Enumeration，存入parameter中，再拼接到后面
//        while (enumeration.hasMoreElements()) {
//            String param = enumeration.nextElement();
//            if ("redirect".equals(param)) {
//                continue;
//            }
//            if ("code".equals(param)) {
//                break;
//            }
//            params.append("&");
//            params.append(param);
//            params.append("=");
//            params.append(request.getParameter(param));
//        }
//        if (StringUtils.isNotBlank(params.toString())) {
//            redirect += params.toString();
//        }
//        if (StringUtils.isBlank(code)) {
//            attr.addFlashAttribute("message", "授权失败");
//            return "redirect:/author/error?direct=" + redirect;
//        }
//        String url = request.getScheme() + request.getServerName();
//        String getAccessTokenUrl = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", Constant.APP_ID, Constant.APP_SERCET, code);
//        String result = restTemplate.getForObject(getAccessTokenUrl, String.class);//获取access_token
//        Map<String, String> mapRsult = JsonMapper.nonDefaultMapper().fromJson(result, Map.class);
//        String accessToken = mapRsult.get("access_token");//网页授权接口调用凭证
//        String refreshToken = mapRsult.get("refresh_token");//用户刷新access_token
//        String openId = mapRsult.get("openid");//用户唯一标识
//
//
//        String expiresIn = mapRsult.get("expires_in");//access_token接口调  用凭证超时时间，单位（秒）
//
//        String getUserInfoUrl = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", accessToken, openId);
//        String userInfo = restTemplate.getForObject(getUserInfoUrl, String.class);//获取用户信息
//        try {
//            userInfo = new String(userInfo.getBytes("iso-8859-1"), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> user = JsonMapper.nonDefaultMapper().fromJson(userInfo, Map.class);
//        String name = (String) user.get("nickname");//用户名
//        Integer sex = (Integer) user.get("sex");//性别
//        String province = (String) user.get("province");//省份
//        String city = (String) user.get("city");//城市
//        String header = (String) user.get("headimgurl");//头像
//
//        TokenUser tokenUser = new TokenUser();//缓存需要的全局属性值
//        JwtUtil jwtUtil = new JwtUtil();
//        String token = JwtUtil.generateToken(tokenUser);
//        Cookie tokenCookie = new Cookie("token", token);
//
//        tokenCookie.setMaxAge(24 * 60 * 60);// 有效期一天
//        tokenCookie.setPath("/");
//        response.addCookie(tokenCookie);
//        return "redirect:" + redirect;
//    }
//
//}
