package com.db.sfs.controller;


import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.GlobalVars;
import com.db.sfs.common.Result;
import com.db.sfs.entity.DBDir;
import com.db.sfs.filehandler.ListFileHandler;
import com.db.sfs.service.ListFileService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileListController {

    @Autowired
    ListFileService listFileService;

    @GetMapping("/getFileList")
    @ResponseBody
    public Result<DBDir> getFileList(String path){
        Result<DBDir> result = new Result<>();
        path = GlobalVars.BASE_DIR + path;
        try{
            DBDir fileList = listFileService.getFileList(path);
            result.ok();
            result.setData(fileList);
        }
        catch (Exception e){
            BaseErrorHandler.doError(result, e.toString());
        }
        return result;
    }
}
