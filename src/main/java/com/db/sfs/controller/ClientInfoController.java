package com.db.sfs.controller;

import com.db.sfs.common.BaseErrorHandler;
import com.db.sfs.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
public class ClientInfoController {

    @GetMapping("/currentDir")
    public Result<String> getCurrentDir(HttpServletRequest request){
        Result<String> result = new Result<>();
        try{
            String currentDir = (String) request.getSession().getAttribute("currentDir");
            result.setData(currentDir);
            result.ok();
        }
        catch (Exception e){
            e.printStackTrace();
            BaseErrorHandler.doError(result);
        }
        return result;
    }

    @PutMapping("/currentDir")
    public Result<String> setCurrentDir(HttpServletRequest request, String currentDir){
        Result<String> result = new Result<>();
        try{
            request.getSession().setAttribute("currentDir", currentDir);
            result.ok();
        }
        catch (Exception e){
            e.printStackTrace();
            BaseErrorHandler.doError(result);
        }
        return result;
    }
}
