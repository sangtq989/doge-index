package com.doge.index.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class MessengerWebhookRequest implements Serializable {
//    @Getter(onMethod=@__({@JsonAlias({"string1", "string2"})}))
//    @JsonAlias({"entry[0].messaging[0].message.reply_to.mid", "replyToMid"})
    private String replyToMsgId;
//    @JsonAlias({"entry[0].messaging[0].message.text", "messageText"})
    private String messageText;
//    @JsonAlias({"entry[0].messaging[0].recipient.id", "recipientId"})
    private String senderId;
}

