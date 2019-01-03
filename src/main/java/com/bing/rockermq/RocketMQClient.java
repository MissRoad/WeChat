package com.bing.rockermq;

import com.bing.config.RocketmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class RocketMQClient {

    @Autowired
    private RocketmqConfig rocketmqConfig;

//    @PostConstruct
    public void defaultMQProducer() {
        //生产者组名
        DefaultMQProducer producer = new DefaultMQProducer(rocketmqConfig.getProducerGroup());
        //指定NameServer地址，多个地址用;隔开
        producer.setNamesrvAddr(rocketmqConfig.getNameServerAddr());
        try {
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();
            //创建一个消息实例，包含 topic、tag 和 消息体
            Message message = new Message("TopicTest", "push", "发送消息".getBytes(RemotingHelper.DEFAULT_CHARSET));
            StopWatch stop = new StopWatch();
            stop.start();
            for (int i = 0; i < 10000; i++) {
                SendResult send = producer.send(message);
                log.info("发送响应：MsgId" + send.getMsgId() + "，发送状态：" + send.getSendStatus());
            }
            stop.stop();
            log.info("发送10000条耗时："+stop.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
