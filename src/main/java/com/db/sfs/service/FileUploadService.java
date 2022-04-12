package com.db.sfs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean uploadMultipartFiles(MultipartFile file, String path);
}
