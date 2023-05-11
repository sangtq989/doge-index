package com.doge.index.controller;

import com.doge.index.dto.request.MessengerWebhookRequest;
import com.doge.index.service.DogeProcessService;
import com.doge.index.service.GoogleDriveService;
import com.doge.index.service.GoogleDriveServiceWebFlux;
import com.doge.index.service.MessengerService;
import com.doge.index.utility.MessengerWebhookObjectMapper;
import com.google.api.services.drive.model.File;
import com.google.common.io.CharStreams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/")
public class IndexDogeController {

    private final MessengerService messengerService;
    private final GoogleDriveService googleDriveService;
    private final GoogleDriveServiceWebFlux googleDriveServiceWebFlux;
    private final DogeProcessService dogeProcessService;
    private final MessengerWebhookObjectMapper messengerWebhookObjectMapper;

    @GetMapping("/messaging-webhook")
    public String verifyMessengerWebhook(@RequestParam("hub.mode") String hubMode,
                                         @RequestParam("hub.verify_token") String verifyToken,
                                         @RequestParam("hub.challenge") String challengeCode) {
        if ("realDoge".equals(verifyToken)) {
            return challengeCode;
        }
        return "403";
    }

    @PostMapping("/messaging-webhook")
    public void receiveMessage(@RequestBody String request) {
        log.info(String.valueOf(request));
        try {
            MessengerWebhookRequest webhookRequest = messengerWebhookObjectMapper.mapMessageRecipient(request);
            dogeProcessService.replyFoundDoge(webhookRequest.getSenderId(), List.of(webhookRequest.getMessageText()));
        } catch (IOException e) {
            log.error("Problem when process Doge", e);
        }
    }

    @GetMapping("/reply")
    public void replyWithDoge(@RequestParam("recipientId") String recipientId,
                              @RequestParam("keywords") List<String> keywords) {
        dogeProcessService.replyFoundDoge(recipientId, keywords);
    }

    @GetMapping("webhook")
    public void reiciveMessage(HttpServletRequest request) {
        request.getHttpServletMapping();
    }


    @GetMapping("/drive/get")
    public List<File> getImage() throws IOException {
        return googleDriveService.retrieveAllFiles();
    }

    @GetMapping("webflux/drive/get")
    public Flux<File> getImageWebflux() {
        return googleDriveServiceWebFlux.retrieveData();
    }

}
