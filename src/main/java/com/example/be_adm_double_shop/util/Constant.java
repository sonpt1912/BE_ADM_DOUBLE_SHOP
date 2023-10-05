package com.example.be_adm_double_shop.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Constant {

    public static final String FORMAT_DATE2 = "dd/MM/yyyy";
    public static final String FORMAT_DATE3 = "yyyyMMdd";
    public static final String FORMAT_DATE4 = "yyyy-MM-dd";
    public static final String FORMAT_DATE5 = "yyyy/MM/dd";

    public static final String FORMAT_TIME2 = "HH:mm:ss";
    public static final String FORMAT_TIME3 = "HHmmss";

    public static final String FORMAT_DATE_TIME2 = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE_TIME3 = "yyyyMMddHHmmss";
    public static final String FORMAT_DATE_TIME4 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME5= "ddMMyyyyHHmmss";
    public static final String FORMAT_DATE_TIME6 = "dd MMM yyyy HH:mm:ss";

    public static final String FORMAT_DB_DATE = "YYYY-MM-DD";
    public static final String FORMAT_DB_TIME = "HH24:MI:SS";
    public static final String FORMAT_DB_DATE_TIME = "YYYY-MM-DD HH24:MI:SS";
    public static final String FORMAT_DB_YDATE_TIME = "SYYYY-MM-DD HH24:MI:SS";

    public static final String FORMAT_MONTH = "MM/yyyy";

    public static final String TIME_ZONE = "GMT+7";


    public static Date timestampToDate(Timestamp stamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(stamp.getTime());
        return cal.getTime();
    }

    public static Date stringToDate(String str) {
        return stringToDate(str, FORMAT_DATE_TIME2);
    }

    public static Date stringToDate4(String str) {
        return stringToDate(str, FORMAT_DATE_TIME4);
    }

    public static String dateToString4(Date date) {
        return dateToString(date, FORMAT_DATE_TIME4);
    }
    public static String dateToString(Date date) {
        return dateToString(date, FORMAT_DATE_TIME2);
    }

    public static String dateToString(LocalDate localDate, String strPattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strPattern);
        String formattedString = localDate.format(formatter);
        return formattedString;
    }

    public static String dateToString(Date date, String strFormat) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
            return simpleDateFormat.format(date);
        } else
            return null;
    }

    public static Date stringToDate(String dateString, String strFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

}
