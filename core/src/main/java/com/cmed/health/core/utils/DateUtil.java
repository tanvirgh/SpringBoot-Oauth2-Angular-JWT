package com.cmed.health.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

public class DateUtil {

    public static Date getDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd//MM/yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }
}
