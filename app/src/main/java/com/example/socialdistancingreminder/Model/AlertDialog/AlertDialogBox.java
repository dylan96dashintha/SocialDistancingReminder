package com.example.socialdistancingreminder.Model.AlertDialog;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;


public class AlertDialogBox {
    public AlertDialog.Builder dialogBuilder;
    private boolean isAlertOpend;

    public boolean isAlertOpend() {
        return isAlertOpend;
    }

    public void setAlertOpend(boolean alertOpend) {
        isAlertOpend = alertOpend;
    }

    public AlertDialogBox(Context context) {
        dialogBuilder = new AlertDialog.Builder(context);
    }

    public void showTrustedDeviceAlertBox(String deviceName, String macAddress) {


        dialogBuilder.setMessage("Are you sure, You wanted to make decision");
        dialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                setAlertOpend(false);
                            }
                        });

        dialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setAlertOpend(false);
                                dialog.cancel();
                            }
                         });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

}
