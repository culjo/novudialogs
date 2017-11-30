package com.novugrid.novudialog.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by appy on 16/11/2017.
 */

public class TimePickedResult {

    private int hour, minute, seconds;

    public TimePickedResult(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTimeString() {

         return hour + ":" + addZeroB4Numb(minute) + ":00"; // this will be sent to the server

    }

    public String getTimeIn12HourFormat() {
        return convert24to12(getTimeString());
    }


    private static String addZeroB4Numb(int number ){
        String numb = String.valueOf(number);
        if(number < 10){
            numb = "0"+number;
        }
        return numb;
    }

    private static String convert24to12(String timeIn24){
        //String s = "12:18:00";
        if(timeIn24 != null) {
            DateFormat f1 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()); //HH for hour of the day (0 - 23)
            Date d = null;
            try {
                d = f1.parse(timeIn24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat f2 = new SimpleDateFormat("h:mm a", Locale.getDefault());
            return f2.format(d).toLowerCase(); // "12:18am"
        }
        else{
            return "";
        }
    }

}
