package com.bing.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RocketmqConfig {
    /**
     * 生产者组名
     */
    @Value("${apache.rocketmq.producerGroup}")
    private String producerGroup;

    /**
     * 消费者组名
     */
    @Value("${apache.rocketmq.PushConsumer}")
    private String PushConsumer;

    /**
     * nameServer地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String nameServerAddr;
}
