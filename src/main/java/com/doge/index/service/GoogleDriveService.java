package com.doge.index.service;

import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.List;

public interface GoogleDriveService{
    List<File> retrieveAllFiles() throws IOException;

    File getDogeById(String dogeId);

    byte[] getDogeByIdAsByte(String dogeId) throws IOException;

}
