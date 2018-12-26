package com.bing.controller;

import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;

/**
 * 消息发送
 *
 * @author fzq
 * @create 2018-01-11 13:17
 */
@RestController
@RequestMapping(value = "/message")
public class WxMessageController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * @Description 群发消息
     * @Author fzq
     * @Date 2018/1/11 13:23
     */
    @GetMapping(value = "/sendAll")
    public WxMpMassSendResult sendAll() throws WxErrorException {
        WxMpMassTagMessage message = new WxMpMassTagMessage();
        message.setMediaId("Pv8clRu6_DhMPYQMtwGvuDmaKhtENthm82PtVa6Lirk");//用于群发的消息的media_id
        message.setMsgType("mpnews");//群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
        message.setSendAll(true);//用于设定是否向全部用户发送，值为true或false，
        message.setSendIgnoreReprint(true);//图文消息被判定为转载时，是否继续群发。
        WxMpMassSendResult messageSend = this.wxMpService.getMassMessageService().massGroupMessageSend(message);
        return messageSend;
    }

    /**
     * 根据OpenID列表群发
     *
     * @Descriptiond
     * @Author fzq
     * @Date 2018/1/15 13:55
     */
    @GetMapping(value = "/sendMsg")
    public WxMpMassSendResult sendTextMsg() throws WxErrorException {
        WxMpMassOpenIdsMessage message = new WxMpMassOpenIdsMessage();
        List<String> userList = new ArrayList<>();
        userList.add("ocxUo1OGQFhlDGrHGvUToIycTGxM");
        userList.add("ocxUo1BpTSt2S7h0M4-5md6UyNig");
        message.setToUsers(userList);//填写图文消息的接收者，一串OpenID列表，OpenID最少2个
        message.setMsgType("mpnews");//群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
//        message.setContent("我发的第一条消息！");//消息内容
        message.setMediaId("Pv8clRu6_DhMPYQMtwGvuDmaKhtENthm82PtVa6Lirk");//图文素材id
        message.setSendIgnoreReprint(false);
        WxMpMassSendResult messageSend = this.wxMpService.getMassMessageService().massOpenIdsMessageSend(message);
        return messageSend;
    }

    public String templateMsg() {
        WxMpTemplateIndustry wxMpTemplateIndustry = new WxMpTemplateIndustry();
        return "";
    }

    /**
     * @Description 获取公众号的自动回复规则
     * @Author fzq
     * @Date 2018/1/15 15:49
     */
    @GetMapping(value = "/getRule")
    public WxMpCurrentAutoReplyInfo getRule() throws WxErrorException {
        WxMpCurrentAutoReplyInfo currentAutoReplyInfo = this.wxMpService.getCurrentAutoReplyInfo();
        System.out.println(currentAutoReplyInfo);
        return currentAutoReplyInfo;
    }

    /**
     * @param openId
     * @param content
     * @return
     * @throws WxErrorException
     */
    @GetMapping(value = "/kefu")
    public String keFuMessage(@RequestParam String openId, @RequestParam String content) throws WxErrorException {
        WxMpKefuMessage message = WxMpKefuMessage.TEXT().
                toUser(openId).content(content).build();
        boolean flag = wxMpService.getKefuService().sendKefuMessage(message);
        return flag ? "发送成功" : "发送失败";
    }

    /**
     * @param templateId
     * @param openId
     * @return
     */
    @GetMapping("/templeMessage")
    public String templeMessage(String templateId, String openId) throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().templateId(templateId)
                .toUser(openId).build();
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("通知","你有新的通知！","#FFFFFF"));
        return wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }
}