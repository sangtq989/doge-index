package com.doge.index.service;

import com.doge.index.entity.Doge;

import java.io.IOException;
import java.util.List;

public interface MessengerService {
    void sendDoges(String recipientId, List<Doge> doges) throws IOException;

}
