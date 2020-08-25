package com.cmed.health.core.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

public class DateUtils {

    public static Date getDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dateString);
        return date;
    }

    public static String format(Date date) {
        return Objects.nonNull(date) ? new SimpleDateFormat("yyyy-MM-dd").format(date) : StringUtils.EMPTY;
    }


    public static Date parseDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) ||
                StringUtils.isEmpty(format)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date parsedDateTime = null;
        try {
            parsedDateTime = sdf.parse(dateStr);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return parsedDateTime;
    }
}
