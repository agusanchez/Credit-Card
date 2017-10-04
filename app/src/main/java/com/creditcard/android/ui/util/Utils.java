package com.creditcard.android.ui.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Agustin on 1/10/2017.
 */

public class Utils {

    public static ProgressDialog getProgressDialog(Context context, int messageRes){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(messageRes));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void showAlertDialog(Context context, String message) {

        new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int stringResId) {
        Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
    }
}
