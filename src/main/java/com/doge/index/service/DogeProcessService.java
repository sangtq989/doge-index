package com.doge.index.service;

import java.io.IOException;
import java.util.List;

public interface DogeProcessService {
    void processFeedDoge();
    void replyFoundDoge(String recipientId, List<String> keywords);
}
