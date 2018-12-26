package com.bing.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/author")
@Slf4j
public class AuthorController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * 获取授权链接
     *
     * @param url
     * @return
     */
    @GetMapping("/url")
    @ResponseBody
    public String url(String url) {
        return wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, "123");
    }

}
