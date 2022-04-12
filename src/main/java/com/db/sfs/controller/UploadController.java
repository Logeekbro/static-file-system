package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class UploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    @ResponseBody
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
