package com.doge.index.service.impl;

import com.doge.index.service.GoogleDriveServiceWebFlux;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleDriveServiceWebfluxImpl implements GoogleDriveServiceWebFlux {

    private final Drive drive;

    @Override
    public Flux<File> retrieveData() {
        return Flux.defer(() -> getOnePageData(null))
                .expand(response -> response.getNextPageToken() != null ? getOnePageData(response.getNextPageToken()) : Mono.empty())
                .flatMapIterable(FileList::getFiles);
    }

    @Override
    public Mono<FileList> getOnePageData(String pageToken) {
        return Mono.fromCallable(() -> {
            Drive.Files.List request = drive.files().list()
                    .setFields("nextPageToken, files(id, name, mimeType)");

            if (pageToken != null) {
                request.setPageToken(pageToken);
            }
            return request.execute();
        });
    }
}
