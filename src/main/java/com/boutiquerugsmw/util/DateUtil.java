package com.boutiquerugsmw.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by furkanbrgl on 04/29/2021.
 */
public class DateUtil {

    /**
     * Converts date object to string as date format "MM/dd/yyyy HH:mm:ss"
     *
     * @param date
     * @return date as String like "10/20/2099 16:16:39"
     */
    public static String formatDateWithTime(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        return dateFormat.format(date);
    }

    /**
     * Converts date object to string as date format "MM.dd.yyyy"
     *
     * @param date
     * @return
     */
    public static String formatDateDotDelimeted(Date date) {

        if (date == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");

        return dateFormat.format(date);
    }

    /**
     * Converts date object to string as date format "MM.dd.yyyy HH:mm"
     *
     * @param date
     * @return
     */
    public static String formatDateUptoMinDotDelimeted(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm");

        return dateFormat.format(date);
    }

    /**
     * Converts date object to string as date format "MM.dd.yyyy HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String formatDateUptoSecDotDelimeted(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");

        return dateFormat.format(date);
    }

    /**
     * @return "MM-dd-yyyy"
     */
    public static String getCurrentDate() {

        String date;
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Calendar calendar = Calendar.getInstance();
        date = dateFormat.format(calendar.getTime());

        return date;
    }

    public static long milisToSecond(long milis) {

        return milis / 1000;
    }
}
