package com.norman.reptile.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /** 2019-08-24 20:33:26 转成时间戳 10位*/
    public static long conversionToSecond(String string) {
        long time = 0;
        try {
            time   = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                    .parse(string, new ParsePosition(0)).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;

    }

    /** 2019-08-24 20:33:26 转成时间戳 13位*/
    public static long conversionToMillisecond(String string) {
        long time = 0;
        try {
            time   = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                    .parse(string, new ParsePosition(0)).getTime() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 时间戳转成string
     * @param num 时间戳 10 或 13 位
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return  2019-08-24 20:33:26
     */
    public static String conversionToString(long num,String format) {
        int length = String.valueOf(num).length();
        String result="";
        if (length==10){
            result = new SimpleDateFormat(format).format(new Date(num * 1000));
        }
        if (length==13){
            result = new SimpleDateFormat(format).format(new Date(num));
        }
        return result;
    }
}
