package com.theah64.retrokit.utils;

import com.theah64.retrokit.retro.RetroKit;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by theapache64 on 16/11/17.
 */

public class DateUtils {

    /**
     * To check if the dates passed are on the same day of some month and year
     *
     * @param date1 Date 1
     * @param date2 Date 2
     * @return True if dates are on the same day, false otherwise.
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Returns date added with one day
     *
     * @param date
     * @return
     */
    public static Date getOneDayAdded(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        System.out.println("Adding day : " + calendar.getTime().toString());
        return calendar.getTime();
    }


    public static Date parseWithDefaultDateFormat(final String date) {
        try {
            return RetroKit.getDefaultDateFormat().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static String formatWithddMMyyyy(final Date date) {
        return RetroKit.getDefaultDateFormat().format(date);
    }

    public static Calendar[] toCalendarArray(List<String> dates) {

        final int totalBusyDays = dates.size();

        final Calendar[] calendars = new Calendar[totalBusyDays];

        for (int i = 0; i < totalBusyDays; i++) {
            final String busyDateString = dates.get(i);
            final Date date = DateUtils.parseWithDefaultDateFormat(busyDateString);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendars[i] = calendar;
        }

        return calendars;
    }

    public static long getDayDifference(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
