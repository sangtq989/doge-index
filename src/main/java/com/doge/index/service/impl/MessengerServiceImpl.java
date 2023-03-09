package com.doge.index.service.impl;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessengerServiceImpl implements MessengerService {
    private final FacebookClient facebookClient;

    public void sendImageFromDrive(String recipientId) throws IOException {
        Resource resource = new ClassPathResource("static/silly_cat.gif");
        Path path = resource.getFile().toPath();
        byte[] bytes = Files.readAllBytes(path);
        BinaryAttachment attachment = BinaryAttachment.with("silly_cat.gif", bytes);

        MediaAttachment messageAttachment = new MediaAttachment(MediaAttachment.Type.IMAGE);
        Message imageMessage = new Message(messageAttachment);
        facebookClient.publish("me/messages", SendResponse.class, attachment,
                Parameter.with("recipient", new IdMessageRecipient(recipientId)),
                Parameter.with("message", imageMessage));
    }
}
