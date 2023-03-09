package com.doge.index.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface GoogleDriveService{
    List<File> retrieveAllFiles() throws IOException;

}
