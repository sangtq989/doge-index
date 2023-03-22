package com.doge.index.service.impl;

import com.doge.index.entity.Doge;
import com.doge.index.exception.CouldNotSendDogeException;
import com.doge.index.repository.DogeRepository;
import com.doge.index.service.DogeProcessService;
import com.doge.index.service.GoogleDriveService;
import com.doge.index.service.MessengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DogeProcessServiceImpl implements DogeProcessService {

    private final GoogleDriveService googleDriveService;
    private final MessengerService messengerService;
    private final DogeRepository dogeRepository;

    @Override
    public void processFeedDoge() {

    }

    @Override
    public void replyFoundDoge(String recipientId, List<String> keywords) {
//        Doge doge = dogeRepository.findByUserAndKeywords(recipientId, keywords);
        List<Doge> doges = dogeRepository.findByUserAndKeywords(keywords);
        log.info("The item by that keyword is {}", doges);
        try {
            messengerService.sendDoges(recipientId, doges);
        } catch (IOException e){
            log.error("Could not get the doge from cage", e);
            throw new CouldNotSendDogeException();
        }
    }
}
