package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.service.FileDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/file")
public class DeleteController {

    @Autowired
    private FileDeleteService fileDeleteService;

    @PostMapping("/delete")
    public Result<String> deleteFile(String path) {
//        path = URLDecoder.decode(path, "utf-8");
        System.out.println("deleteFileUrl:" + path);
        Result<String> result = new Result<>();
        try{
            fileDeleteService.deleteFile(path);
            result.ok();
        }
        catch (Exception e){
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }

    @PostMapping("/dir/delete")
    public Result<String> deleteDir(String path) throws UnsupportedEncodingException {
//        path = URLDecoder.decode(path, "utf-8");
        System.out.println("deleteDirPath:" + path);
        Result<String> result = new Result<>();
        try{
            fileDeleteService.deleteDir(path);
            result.ok();
        }
        catch (Exception e){
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }
}
