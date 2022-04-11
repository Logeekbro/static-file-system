package com.db.sfs.service.impl;

import com.db.sfs.filehandler.UploadHandler;
import com.db.sfs.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    UploadHandler uploadHandler;

    @Override
    public boolean uploadMultipartFiles(MultipartFile file) {
        return uploadHandler.uploadMultipartFiles(file);
    }
}
