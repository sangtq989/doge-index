package com.doge.index.service.impl;

import com.doge.index.service.GoogleDriveService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final Drive drive;

    @Override
    public List<File> retrieveAllFiles() throws IOException {
        String folderId = "1sADaU1lznbD-LRSuGKsnUxbkG1E0-smO";
        String query = "'" + folderId + "' in parents";
        FileList result = drive.files().list()
                .setQ(query)
                .setFields("nextPageToken, files(id, name, createdTime, modifiedTime, mimeType)")
                .execute();
        return result.getFiles();
    }

    @Override
    public File getDogeById(String dogeId) {
        return null;
    }

    @Override
    public byte[] getDogeByIdAsByte(String dogeId) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        drive.files().get(dogeId).executeMedia().download(outputStream);
        return outputStream.toByteArray();
    }
}
