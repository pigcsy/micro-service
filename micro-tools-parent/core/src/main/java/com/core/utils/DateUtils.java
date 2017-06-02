package com.core.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static Date parseString2Date(String source, String format) {
        Date date = null;
        try {
            Asserts.notNull(source, "Date");
            Asserts.notNull(format, "format");
            SimpleDateFormat df = new SimpleDateFormat(format);
            date = df.parse(source);
        } catch (ParseException e) {
            logger.error("字符串" + source + "转为格式为" + format + "日期格式出错", e);
        }
        return date;
    }


    public static String date2String(Date date, String format) {
        String str = null;
        try {
            Asserts.notNull(date, "Date");
            Asserts.notNull(format, "format");
            SimpleDateFormat df = new SimpleDateFormat(format);
            str = df.format(date);
        } catch (Exception e) {
            logger.error("日期" + date + "转为格式为" + format + "日期格式出错", e);
        }
        return str;
    }

    /**
     * 求所给日期的前/后 num 天/月/年
     *
     * @param sourceDate
     * @param num        数字 num>0 ,日期后，num<0 日期前
     * @param type       0：天，1：月，2：年
     */
    public static Date beforeDate(Date sourceDate, int num, int type) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        if (type == 0) {
            c.add(Calendar.DAY_OF_YEAR, num);
        } else if (type == 1) {
            c.add(Calendar.MONTH, num);
        } else if (type == 1) {
            c.add(Calendar.YEAR, num);
        }
        return c.getTime();
    }


    public static Date getFirstDayOfMonth(Date sourceDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date parseNginxDate(String ndate) {
        if (StringUtils.isEmpty(ndate)) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
            Date date = formatter.parse(ndate);
            return date;
        } catch (Exception e) {
            logger.error("日期" + ndate + "转为格式为日期格式出错", e);
        }
        return null;
    }

    public static void main(String[] args) {
        Date d = new Date();
        d = getFirstDayOfMonth(d);
        System.out.println(date2String(d, "yyyyMMdd"));
    }
}
