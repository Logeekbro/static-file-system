package com.db.sfs.controller;

import com.db.sfs.common.GlobalVars;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/env")
public class EnvironmentInfoController {

    @GetMapping("/getFileSep")
    @ResponseBody
    public String getFileSep(){
        return GlobalVars.FILE_SEP;
    }
}
