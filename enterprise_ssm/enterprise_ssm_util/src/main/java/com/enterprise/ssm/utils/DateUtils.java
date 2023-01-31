package com.enterprise.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转字符串格式
     * @param date 日期
     * @param patten  格式
     * @return
     */
    public static String data2String(Date date,String patten){
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        String format= sdf.format(date);
        return format;

    }


    /**
     * 字符串转日期格式
     * @param str  要抓转换的字符串
     * @param patten  转化日期的格式
     * @return 返回日期
     * @throws ParseException
     */
    public static Date string2Date(String str,String patten) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        Date date=sdf.parse(str);
        return date;
    }
}
