package com.db.sfs.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeUtils {
    public static String getDate(long timestamp){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(timestamp);
    }
    public static String getDateTime(long timestamp){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(timestamp);
    }
    public static Long dateToStamp(String s) throws ParseException {
        //设置时间模版
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = simpleDateFormat.parse(s);
        return date.getTime()/1000L;
    }
}
