package com.bing.rockermq;

import com.bing.config.RocketmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class RocketMQServer {
    @Autowired
    RocketmqConfig rocketmqConfig;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器调用一次，类似于Serclet的inti()方法。
     *  被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     *   @PostConstruct
     */
    public void defaultMQPushConsumer() {
        //消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqConfig.getPushConsumer());
       //消费者指定NameServer地址
        consumer.setNamesrvAddr(rocketmqConfig.getNameServerAddr());
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("TopicTest", "push");
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, contex) -> {
                try {
                    for (MessageExt r : list) {
                        log.info("messageExt" + r);
                        log.info("消息响应msgId:" + r.getMsgId() + "msgBody:" + r.getBody());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
