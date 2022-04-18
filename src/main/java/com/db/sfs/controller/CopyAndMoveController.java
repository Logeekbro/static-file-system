package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import com.db.sfs.service.CopyAndMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class CopyAndMoveController {

    @Autowired
    private CopyAndMoveService copyAndMoveService;

    @PostMapping("/copy")
    public Result<String> copy(String source, String target){
        Result<String> result = new Result<>();
        try{
            copyAndMoveService.copy(source, target);
            result.ok();
        }
        catch (Exception e){
            e.printStackTrace();
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }

    @PostMapping("/move")
    public Result<String> move(String source, String target){
        Result<String> result = new Result<>();
        try{
            copyAndMoveService.move(source, target);
            result.ok();
        }
        catch (Exception e){
            e.printStackTrace();
            BaseErrorHandler.doError(result, e.getMessage());
        }
        return result;
    }
}
