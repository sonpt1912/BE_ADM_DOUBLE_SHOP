package com.example.be_adm_double_shop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StringUtil {

    public static String nvl(Object objInput, String strNullValue) {
        if (objInput == null)
            return strNullValue;
        return objInput.toString();
    }

    public static String generatorCurrentDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATE_TIME4);
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    public static boolean stringIsNullOrEmty(String str) {
        if (str == null)
            return true;
        else {
            if (str.trim().length() <= 0)
                return true;
        }
        return false;
    }

    public static boolean stringIsNullOrEmty(Object str) {
        if (str == null)
            return true;
        else {
            if (str.toString().trim().length() <= 0)
                return true;
        }
        return false;
    }

    public static boolean isListEmpty(List lst) {
        return lst == null || lst.isEmpty();
    }

}
