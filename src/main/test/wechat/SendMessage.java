package wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SendMessage extends SpringTest {

    @Autowired
    private WxMpService wxMpService;

    @Test
    public void keFuMessage() throws WxErrorException {
        WxMpKefuMessage message = WxMpKefuMessage.TEXT().
            toUser("oE9qI1GNcxSNkJAJ5HbnCZp9vwgw").content("hello word!").build();
        boolean flag = wxMpService.getKefuService().sendKefuMessage(message);
        log.info(flag ? "发送成功" : "发送失败");
    }
}
