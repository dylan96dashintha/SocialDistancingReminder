package com.example.socialdistancingreminder.Model.AlertDialog;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;


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

    public void showTrustedDeviceAlertBox(String deviceName, String macAddress, DBconnection dbconnection) {


        dialogBuilder.setMessage("Are you sure, You wanted to add this device  "+ deviceName+" With macaddress"+ macAddress);
        dialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                dbconnection.insertData(macAddress, setDeviceName(deviceName), "1");
                                setAlertOpend(false);
                            }
                        });

        dialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbconnection.insertData(macAddress, setDeviceName(deviceName), "0");
                                setAlertOpend(false);
                                dialog.cancel();
                            }
                         });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    public String setDeviceName(String deviceName) {
        if (deviceName == null) {
            deviceName = "unknown";
        }
        return deviceName;
    }

}
