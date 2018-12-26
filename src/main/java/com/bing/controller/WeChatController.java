package com.bing.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对话服务(入口)
 *
 * @author fzq
 * @create 2018-01-09 16:57
 */
@RestController
@RequestMapping(value = "/portal")
@Slf4j
public class WeChatController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter router;


    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr
    ) {
        this.LOG.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数不合法，请核实！");
        }
        if (this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "非法请求";
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        this.LOG.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
            }
            //自定义回复消息
            if ("1".equals(inMessage.getContent())) {
                WxMpXmlOutTextMessage test = WxMpXmlOutMessage.TEXT().content("妈的制杖").fromUser(inMessage.getToUser())
                        .toUser(inMessage.getFromUser()).build();
                out = test.toXml();
            } else if ("2".equals(inMessage.getContent())) {
                WxMpXmlOutTextMessage test = WxMpXmlOutTextMessage.TEXT().content("<a href='http://pic.netbian.com'></a>").fromUser(inMessage.getToUser())
                        .toUser(inMessage.getFromUser()).build();
                out = test.toXml();
            } else if ("3".equals(inMessage.getContent())) {
                out = WxMpXmlOutMessage.IMAGE().mediaId("Pv8clRu6_DhMPYQMtwGvuPSZXAwLnZ2Wxxhixm35UHc").fromUser(inMessage.getToUser())
                        .toUser(inMessage.getFromUser()).build().toXml();
            }
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                    requestBody, this.wxMpService.getWxMpConfigStorage(), timestamp,
                    nonce, msgSignature);
            this.LOG.info("\n消息解密后内容为：\n{} ", inMessage.toString());
            System.out.println("消息解密后内容为：" + inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            } else if (outMessage.equals(1)) {
                return "收到消息了啊！";
            }
            out = outMessage
                    .toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
        }
        this.LOG.info("\n组装回复信息：{}", out);
        return out;
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.router.route(message);
        } catch (Exception e) {
            this.LOG.error(e.getMessage(), e);
        }
        return null;
    }

}