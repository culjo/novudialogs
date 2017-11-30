package com.novugrid.novudialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.novugrid.novudialog.R;

/**
 * Created by appy on 21/11/2017.
 */

public class NovuLoadingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View layout = getActivity().getLayoutInflater().inflate(R.layout.dialog_type, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

//        builder.setTitle("Your Status Message (140)");
        builder.setCancelable(false);
        builder.setView(layout);
//        builder.setPositiveButton("Save", positiveBtnClick);
//        builder.setNegativeButton("Cancel", negativeBtnClick);

        return builder.create(); //AlertDialog dailog = builder.create();

    }
}
