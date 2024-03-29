package com.db.sfs.controller;

import com.db.sfs.common.GlobalVars;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvironmentInfoController {

    @GetMapping("/getFileSep")
    public String getFileSep(){
        return GlobalVars.FILE_SEP;
    }
}
