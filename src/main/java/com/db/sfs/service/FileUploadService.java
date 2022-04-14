package com.db.sfs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    void uploadMultipartFiles(MultipartFile file, String path) throws Exception;
}
