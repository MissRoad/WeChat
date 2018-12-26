package com.bing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 微信授权
 *
 * @author fzq
 * @create 2018-01-15 14:26
 */
@RestController
@RequestMapping(value = "/author")
public class WxMpAuthorController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * @Description 获取授权
     * @Author fzq
     * @Date 2018/1/15 14:27
     */
    @GetMapping(value = "/getUser")
    public WxMpUser getUserInfo() throws WxErrorException {
        WxMpUser u = this.wxMpService.getUserService().userInfo("ocxUo1OGQFhlDGrHGvUToIycTGxM");
        return u;
    }

    /**
     * @Description 获取授权
     * @Author fzq
     * @Date 2018/1/15 14:45
     */
    @GetMapping(value = "/return")
    public String refreshToken() throws WxErrorException {
        return this.wxMpService.getAccessToken();
    }
}