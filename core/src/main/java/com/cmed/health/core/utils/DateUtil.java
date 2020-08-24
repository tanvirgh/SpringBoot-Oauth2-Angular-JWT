package com.cmed.health.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author razib
 * @date 8/15/18
 * @email fakrul@impelitsolutions.com
 */

public class DateUtil {

    public static Date getDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd//MM/yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }
}
