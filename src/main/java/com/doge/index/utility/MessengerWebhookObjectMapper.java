package com.doge.index.utility;

import com.doge.index.dto.request.MessengerWebhookRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.types.send.MessageRecipient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class MessengerWebhookObjectMapper extends ObjectMapper {
    public MessengerWebhookObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public MessengerWebhookRequest mapMessageRecipient(String json) throws IOException {
        JsonNode rootNode = this.readTree(json);
        JsonNode messageNode = rootNode.at("/entry/0/messaging/0/message");
        JsonNode recipientNode = rootNode.at("/entry/0/messaging/0/sender");
        String replyTo = Optional.ofNullable(messageNode.get("reply_to"))
                .map(s -> s.get("mid").asText())
                .orElse("");

        return MessengerWebhookRequest.builder()
                .replyToMsgId(replyTo)
                .messageText(messageNode.get("text").asText())
                .senderId(recipientNode.get("id").asText())
                .build();


    }
}
