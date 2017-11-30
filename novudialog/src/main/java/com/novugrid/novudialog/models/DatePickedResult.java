package com.novugrid.novudialog.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by appy on 16/11/2017.
 */

public class DatePickedResult {

    private int year, month, dayOfMonth;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDateString() {

        String dateString = "";
        DateFormat format = new SimpleDateFormat("y-M-d", Locale.getDefault());
        try {
            Date d = format.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
            DateFormat format2 = new SimpleDateFormat("y-MM-d", Locale.getDefault());
            dateString = format2.format(d);

        }catch (ParseException e){
            e.printStackTrace();
        }

        return dateString;

    }

    public String getReadableDate() {

        DateFormat format = new SimpleDateFormat("y-MM-dd", Locale.getDefault());
        Date d = null;
        try {
            d = format.parse(getDateString());
        }catch (ParseException e){
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("d, MMM y", Locale.getDefault());
        return format2.format(d);

    }

    public long getDateInTimeStamp() {
        return 0;
    }

    /**
     * Not Implemented Yet
     * @param format the string format wildcards
     * @return empty string
     */
    public String formatDate(String format) {
        return "";
    }

}
