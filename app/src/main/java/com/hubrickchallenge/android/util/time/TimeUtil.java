package com.hubrickchallenge.android.util.time;

import android.content.Context;

import com.hubrickchallenge.android.R;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class TimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final int ONE_WEEK_IN_DAYS = 7;
    private static final int ONE_MONTH_IN_DAYS = 30;
    private static final int ONE_YEAR_IN_DAYS = 365;

    private TimeUtil() {
        throw new AssertionError();
    }

    public static String dateTimeToString(DateTime dateTime) {
        return dateTime.toString(DATE_TIME_FORMATTER);
    }

    public static DateTime dateTimeFromString(String dateTime) {
        return DateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    /**
     * The "future" DateTime object is being presented as "current" in order to avoid any confusion.
     */
    public static String getDateTimeDifference(Context context, ReadableInstant past) {
        DateTime now = new DateTime();
        if (past.isAfter(now)) {
            return context.getString(R.string.date_time_difference__now);
        }
        Duration duration = new Duration(past, now);
        int durationInDays = (int) duration.getStandardDays();
        if (durationInDays >= ONE_YEAR_IN_DAYS) {
            int durationInYears = durationInDays / ONE_YEAR_IN_DAYS;
            return context.getResources().getQuantityString(R.plurals.date_time_difference__year,
                    durationInYears, durationInYears);
        } else if (durationInDays >= ONE_MONTH_IN_DAYS) {
            int durationInMonths = durationInDays / ONE_MONTH_IN_DAYS;
            return context.getResources().getQuantityString(R.plurals.date_time_difference__month,
                    durationInMonths, durationInMonths);
        } else if (durationInDays >= ONE_WEEK_IN_DAYS) {
            int durationInWeeks = durationInDays / ONE_WEEK_IN_DAYS;
            return context.getResources().getQuantityString(R.plurals.date_time_difference__week,
                    durationInWeeks, durationInWeeks);
        } else if (durationInDays >= 1) {
            return context.getResources().getQuantityString(R.plurals.date_time_difference__day,
                    durationInDays, durationInDays);
        } else {
            int durationInHours = (int) duration.getStandardHours();
            if (durationInHours == 0) {
                int durationInMinutes = (int) duration.getStandardMinutes();
                if (durationInMinutes == 0) {
                    int durationInSeconds = (int) duration.getStandardSeconds();
                    if (durationInSeconds == 0) {
                        return context.getString(R.string.date_time_difference__now);
                    } else {
                        return context.getResources().getQuantityString(R.plurals.date_time_difference__second,
                                durationInSeconds, durationInSeconds);
                    }
                } else {
                    return context.getResources().getQuantityString(R.plurals.date_time_difference__minute,
                            durationInMinutes, durationInMinutes);
                }
            } else {
                return context.getResources().getQuantityString(R.plurals.date_time_difference__hour,
                        durationInHours, durationInHours);
            }
        }
    }

}
