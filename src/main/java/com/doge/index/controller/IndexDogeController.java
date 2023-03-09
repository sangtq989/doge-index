package com.doge.index.controller;

import com.doge.index.service.MessengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/")
public class IndexDogeController {

    private final MessengerService messengerService;

    @GetMapping("ok")
    public void testApi(@RequestParam String id) throws IOException {
        messengerService.sendImageFromDrive(id);
    }
}
