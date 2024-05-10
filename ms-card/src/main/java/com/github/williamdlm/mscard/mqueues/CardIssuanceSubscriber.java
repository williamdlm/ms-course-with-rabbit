package com.github.williamdlm.mscard.mqueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.williamdlm.mscard.model.Card;
import com.github.williamdlm.mscard.model.ClientCard;
import com.github.williamdlm.mscard.pojo.RequestIssuanceCardData;
import com.github.williamdlm.mscard.repository.CardRepository;
import com.github.williamdlm.mscard.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;
    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void requestIssuance(@Payload String payload) {
        var mapper = new ObjectMapper();
        try {
            RequestIssuanceCardData data = mapper.readValue(payload, RequestIssuanceCardData.class);
           Card card = cardRepository.findById(data.getIdCard()).orElseThrow();
            ClientCard clientCard =  new ClientCard();
            clientCard.setCard(card);
            clientCard.setDocument(data.getDocument());

            clientCardRepository.save(clientCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
