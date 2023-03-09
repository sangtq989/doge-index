package com.doge.index.service.impl;

import com.doge.index.service.GoogleDriveService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final Drive drive;

    @Override
    public List<File> retrieveAllFiles() throws IOException {
        return drive.files().list()
                .setFields("nextPageToken, files(id, name)")
                .execute()
                .getFiles();
    }
}
