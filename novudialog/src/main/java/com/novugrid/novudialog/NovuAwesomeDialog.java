package com.novugrid.novudialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by appy on 19/11/2017.
 * todo: We will create a custom dialog that will morph better to different scenarios
 * Like Progress, Single Button, Delete, Confirmation, Alert, Warning.. e.t.c
 */
public class NovuAwesomeDialog extends DialogFragment {

    private String titleText, contentText, confirmButtonText = "Ok", cancelButtonText = "Cancel", neutralButtonText = "";

    public interface OnAwesomeClickListener{
        void onClick(NovuAwesomeDialog novuAwesomeDialog);
    }

    private OnAwesomeClickListener confirmClickListener;
    private OnAwesomeClickListener cancelClickListener;
    private OnAwesomeClickListener neutralClickListener;

    public NovuAwesomeDialog setConfirmClickListener(OnAwesomeClickListener onAwesomeClickListener) {
        confirmClickListener = onAwesomeClickListener;
        return this;
    }
    public NovuAwesomeDialog setCancelClickListener(OnAwesomeClickListener onAwesomeClickListener) {
        cancelClickListener = onAwesomeClickListener;
        return this;
    }

    public NovuAwesomeDialog setNeutralClickListener(OnAwesomeClickListener onAwesomeClickListener) {
        neutralClickListener = onAwesomeClickListener;
        return this;
    }

    public static NovuAwesomeDialog newInstance(String title, String message){
        NovuAwesomeDialog fragDialog = new NovuAwesomeDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        fragDialog.setArguments(args);
        return fragDialog;
    }

    public NovuAwesomeDialog setTitleText(String title){
        this.titleText = title;
        return this;
    }

    public NovuAwesomeDialog setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public NovuAwesomeDialog setConfirmButtonText(String confirmButtonText){
        this.confirmButtonText = confirmButtonText;
        return this;
    }
    public NovuAwesomeDialog setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
        return this;
    }
    public NovuAwesomeDialog setNeutralButtonText(String neutralButtonText) {
        this.neutralButtonText = neutralButtonText;
        return this;
    }

    public void show() {
        // TODO: 19/11/2017 we will inplement a method chaining that will make this process cool
        this.setCancelable(false);
        this.show(getFragmentManager(), "");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*try{
            if(getTargetFragment() != null){//Use the fragment as the host
                listener = (ConfirmationDialogListener) getTargetFragment();
            }else {
                listener = (ConfirmationDialogListener) context;
            }
        }catch (ClassCastException e){
//            throw new ClassCastException(activity.toString() +
//                    " Must Implement ConfirmationDialog Listener");

        }*/
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (titleText != null) builder.setTitle(titleText);
        if (contentText != null) builder.setMessage(contentText);//getArguments().getString("message"));


        if (confirmClickListener != null){

            builder.setPositiveButton(confirmButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    confirmClickListener.onClick(NovuAwesomeDialog.this);
                }
            });

        }else {
            builder.setPositiveButton(confirmButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
        }

        if (cancelClickListener != null) {

            builder.setNegativeButton(cancelButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancelClickListener.onClick(NovuAwesomeDialog.this);
                }
            });

        }

        if (neutralClickListener != null) {

            builder.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    neutralClickListener.onClick((NovuAwesomeDialog) dialog);
                }
            });

        }
        setCancelable(false);

        return builder.create();

    }




}
