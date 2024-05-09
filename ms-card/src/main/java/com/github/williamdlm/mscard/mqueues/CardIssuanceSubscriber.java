package com.github.williamdlm.mscard.mqueues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuanceSubscriber {
@RabbitListener(queues = "${mq.queues.card-issuance}")
    public void requestIssuance(@Payload String payload){
        System.out.println(payload);
    }
}