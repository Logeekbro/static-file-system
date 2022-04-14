package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.service.DirCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class CreateController {

    @Autowired
    private DirCreateService dirCreateService;

    @PostMapping("/dir/create")
    public Result<String> createDir(String path){
        Result<String> result = new Result<>();
        try{
            dirCreateService.createDir(path);
            result.ok();
        }
        catch (Exception e){
            e.printStackTrace();
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }
}
