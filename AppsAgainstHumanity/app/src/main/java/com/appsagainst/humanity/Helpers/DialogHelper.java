package com.appsagainst.humanity.Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogHelper {

    ProgressDialog progressDialog;

    static DialogHelper dialogHelper;
    public static DialogHelper getInstance(){
        if(dialogHelper == null){
            dialogHelper = new DialogHelper();
        }

        return dialogHelper;
    }

    protected DialogHelper(){

    }

    public static void displayWinnerDialog(Activity act, boolean didWin, String winnerName, final DialogCallback callback){
        String displayTitle;
        String message;

        if(didWin){
            displayTitle = "You Won!";
            message = "Congratulations, you won this round. Click to continue.";
        } else {
            displayTitle = "You Lost!";
            message = "Unlucky you lost this one. " + winnerName + " won. Click to continue.";
        }

        new AlertDialog.Builder(act)
                .setTitle(displayTitle)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callback != null) {
                            callback.positiveClick();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if (callback != null) {
                            callback.negativeClick();
                        }
                    }
                })
                .show();
    }

    public static void displayConfirmWinnerDialog(Activity act, final DialogCallback callback){
        new AlertDialog.Builder(act)
                .setTitle("You sure?")
                .setMessage("You sure you want to choose this winner?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callback != null) {
                            callback.positiveClick();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if (callback != null) {
                            callback.negativeClick();
                        }
                    }
                })
                .show();
    }

    public void displayProgressDialog(Activity act, String title, String message){
        progressDialog = ProgressDialog.show(act, title, message, true);
    }

    public void hideProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public interface DialogCallback{
        void positiveClick();
        void negativeClick();
    }
}
