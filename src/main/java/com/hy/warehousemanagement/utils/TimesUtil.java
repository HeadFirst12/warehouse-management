package com.hy.warehousemanagement.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimesUtil {

    //24小时时间格式
    public static final String NORMAL_DATE_24_HOUR_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //12小时时间格式
    public static final String NORMAL_DATE_12_HOUR_FORMAT = "yyyy-MM-dd hh:mm:ss";

    //短时间格式
    public static final String SHORT_DATE_FORMAT = "yyy-MM-dd";

    /**
     * Date格式转化为String格式
     * @param date
     * @param format
     * @return
     */
    public static String DateToStringFormat(Date date,String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

}
