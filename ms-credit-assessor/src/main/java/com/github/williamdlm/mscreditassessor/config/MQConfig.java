package com.github.williamdlm.mscreditassessor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.card-issuance}")
    private String issuanceCardQueue;

    @Bean
    public Queue queueIssuanceCard() {
        return new Queue(issuanceCardQueue, true);
    }
}
