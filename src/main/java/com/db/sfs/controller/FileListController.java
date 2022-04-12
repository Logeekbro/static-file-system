package com.db.sfs.controller;


import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.entity.DBDir;
import com.db.sfs.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/file")
public class FileListController {

    @Autowired
    FileListService fileListService;

    @GetMapping("/getFileList")
    @ResponseBody
    public Result<DBDir> getFileList(@RequestParam(value = "path", defaultValue = "") String path) throws UnsupportedEncodingException {
        Result<DBDir> result = new Result<>();
        // 将前端经过URL编码的path解码
        path = URLDecoder.decode(path,"utf-8");
        try{
            DBDir fileList = fileListService.getFileList(path);
            result.ok();
            result.setData(fileList);
        }
        catch (Exception e){
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }
}
