package com.novugrid.novudialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.novugrid.novudialog.models.TimePickedResult;

import java.util.Calendar;

/**
 * Created by WeaverBird on 3/5/2016.
 * author Olad Lekan
 */
public class NovuTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public interface TimePickerListener{
        //void onTimeChosen(int hourOfDay, int minute, String tag);
        void onTimeChosen(TimePickedResult timePickedResult);
    }

    TimePickerListener timePickerListener;

    public static NovuTimePicker newInstance() { return new NovuTimePicker(); }

    public static NovuTimePicker newInstance(boolean is12HourClock) {
        NovuTimePicker novuTimePicker = NovuTimePicker.newInstance();
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_12_hour_clock", is12HourClock);
        novuTimePicker.setArguments(bundle);
        return novuTimePicker;
    }

    public NovuTimePicker setTimePickerListener(TimePickerListener timePickerListener) {
        this.timePickerListener = timePickerListener;
        return this;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            if(getTargetFragment() != null) {
                timePickerListener = (TimePickerListener) getTargetFragment();
            }else{
                timePickerListener = (TimePickerListener) context;
            }
        }catch (ClassCastException e){
            //throw new ClassCastException(context.toString() + "Must Implement MultiChoice Dialog Listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        if (timePickerListener == null) return;
//        timePickerListener.onTimeChosen(hourOfDay, minute, this.getTag());
        timePickerListener.onTimeChosen(new TimePickedResult(hourOfDay, minute));
    }

}
