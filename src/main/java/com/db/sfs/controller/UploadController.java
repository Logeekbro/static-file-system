package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file, @RequestParam(name = "path", defaultValue = "") String path){
        System.out.println("upload path:" + path);
        Result<String> result = new Result<>();
        if(fileUploadService.uploadMultipartFiles(file, path)){
            result.ok();
        }
        else {
            BaseErrorHandler.doError(result);
        }
        return result;
    }
}
