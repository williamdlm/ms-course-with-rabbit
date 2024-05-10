package com.github.williamdlm.mscreditassessor.mqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.williamdlm.mscreditassessor.pojo.RequestIssuanceCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssuanceCardPublisher {
    private final RabbitTemplate rabbitTemplate;

    private final Queue queueIssuanceCard;

    public void issuanceCard(RequestIssuanceCardData reqCardData) throws JsonProcessingException {
        var json = toJson(reqCardData);
        rabbitTemplate.convertAndSend(queueIssuanceCard.getName(), json);
    }

    private String toJson(RequestIssuanceCardData obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(obj);
        return json;
    }
}
