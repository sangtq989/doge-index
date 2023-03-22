package com.doge.index.service;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface GoogleDriveServiceWebFlux {
    Flux<File> retrieveData();

    Mono<FileList> getOnePageData(String pageToken);
}
