package com.doge.index.service.impl;

import com.doge.index.entity.Doge;
import com.doge.index.service.GoogleDriveService;
import com.doge.index.service.MessengerService;
import com.restfb.BinaryAttachment;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.MediaAttachment;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessengerServiceImpl implements MessengerService {
    private final FacebookClient facebookClient;
    private final GoogleDriveService googleDriveService;

    public void sendDoges(String recipientId, List<Doge> doges) {
        for (Doge doge : doges) {
            byte[] dogeData;
            try {
                dogeData = googleDriveService.getDogeByIdAsByte(doge.getDriveId());
                BinaryAttachment sentDoge = BinaryAttachment.with(doge.getTitle(), dogeData);
                MediaAttachment messageAttachment = new MediaAttachment(MediaAttachment.Type.IMAGE);
                Message imageMessage = new Message(messageAttachment);
                facebookClient.publish("me/messages", SendResponse.class, sentDoge,
                        Parameter.with("recipient", new IdMessageRecipient(recipientId)),
                        Parameter.with("message", imageMessage));
            } catch (IOException e) {
                log.error("Could not sent doge to {}", recipientId, e);
            }
        }
    }
}
