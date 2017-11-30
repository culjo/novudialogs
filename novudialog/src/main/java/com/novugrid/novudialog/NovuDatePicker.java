package com.novugrid.novudialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.novugrid.novudialog.models.DatePickedResult;

import java.util.Calendar;

/**
 * Created by WeaverBird on 3/6/2016.
 * For displaying date picker fragment to the user
 */
public class NovuDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public interface DatePickerListener{
        //void onDateChosen(DatePicker view, int year, int monthOfYear, int dayOfMonth);
        void onDateChosen(DatePickedResult datePickedResult);
    }

    DatePickerListener datePickerListener;


    /** We use this to pass in parameter to the dialog fragment */
    public static NovuDatePicker newInstance(boolean todayIsMin){

        NovuDatePicker fragDialog = NovuDatePicker.newInstance();
        Bundle args = new Bundle(); args.putBoolean("today_is_min", todayIsMin);
        fragDialog.setArguments(args);

        return fragDialog;
    }

    public static NovuDatePicker newInstance() {
        return new NovuDatePicker();
    }

    public NovuDatePicker setDatePickerListener(DatePickerListener datePickerListener) {
        this.datePickerListener = datePickerListener;
        return this;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            datePickerListener = (DatePickerListener) context;
        }catch (ClassCastException e){ e.printStackTrace(); }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), this, year, month, day);

        if(getArguments() != null){
            if(getArguments().getBoolean("today_is_min")) {
                dateDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            }
        }

        return dateDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //datePickerListener.onDateChosen(view, year, monthOfYear, dayOfMonth);

        DatePickedResult datePickedResult = new DatePickedResult();
        datePickedResult.setYear(year);
        datePickedResult.setMonth(monthOfYear);
        datePickedResult.setDayOfMonth(dayOfMonth);

        datePickerListener.onDateChosen(datePickedResult);

    }


}
