package com.db.sfs.common;


import org.springframework.stereotype.Component;


@Component
public class BaseErrorHandler {

    public static void doError(Result result){
        result.error();
        result.setMsg("unknown error");
    }

    public static void doError(Result result, String errorMsg){
        result.error();
        result.setMsg(errorMsg);
    }

}
