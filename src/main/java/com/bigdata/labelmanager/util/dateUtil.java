package com.bigdata.labelmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtil {
    public static String getDate()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date=df.format(new Date());
        return date;
    }

}
