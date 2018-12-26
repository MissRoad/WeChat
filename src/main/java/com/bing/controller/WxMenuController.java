package com.bing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;

/**
 * @author fzq
 * @create 2018-01-09 13:09
 */
@RestController
@RequestMapping("/menu")
public class WxMenuController {

    @Autowired
    private WxMpService wxMpService;

    private String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4dcd874efb071093&redirect_uri=http%3A%2F%2Ffzq.qianlxlinux.com%2Fbind%2Findex&response_type=" +
            "code&scope=snsapi_userinfo&state=123#wechat_redirect";

    @GetMapping("/create")
    public String crate() throws WxErrorException {
        WxMenu wxMenu = new WxMenu();
        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.MenuButtonType.VIEW);
        button2.setName("绑定用户信息");
        button2.setUrl(url);
        wxMenu.getButtons().add(button2);

        return wxMpService.getMenuService().menuCreate(wxMenu);
    }

    /**
     * @Description 获取菜单信息
     * @Author fzq
     * @Date 2018/1/19 16:06
     */
    @GetMapping(value = "getMenu")
    public WxMpGetSelfMenuInfoResult getMenu() throws WxErrorException {
        WxMpGetSelfMenuInfoResult selfMenuInfo = this.wxMpService.getMenuService().getSelfMenuInfo();
        System.out.println(selfMenuInfo);
        return selfMenuInfo;
    }

}