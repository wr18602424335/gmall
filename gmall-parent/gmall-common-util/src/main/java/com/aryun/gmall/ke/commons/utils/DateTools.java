package com.aryun.gmall.ke.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: liuquanxing
 * create: 2019-06-12
 * description: life was like a box of chocolate
 **/
public class DateTools {
    private static final String CRON_FORMAT = "ss mm HH dd MM ? yyyy";

    public static String date2CronExpression(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_FORMAT);
        return sdf.format(date);
    }

    public static boolean afterCurrent(Date date) {
        return date.after(new Date());
    }
}
