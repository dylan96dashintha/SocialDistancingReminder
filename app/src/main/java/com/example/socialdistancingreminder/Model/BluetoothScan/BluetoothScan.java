package com.example.socialdistancingreminder.Model.BluetoothScan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdistancingreminder.Model.AlertDialog.AlertDialogBox;
import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;

import java.util.ArrayList;

public class BluetoothScan extends AppCompatActivity implements Runnable {
    private static final String TAG = "BluetoothScan";
    public Context context;
    private static boolean isFinished;
    private static DBconnection dbconnection;
    private static AlertDialogBox alertDialogBox;
    //public boolean isAlertOpened;
    ArrayList<BluetoothDevice> foundDevices;

    BluetoothAdapter BTAdapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public BluetoothScan(Context context) {
        this.context = context;
        isFinished = true;
        dbconnection = new DBconnection(context); //DB connection
        alertDialogBox = new AlertDialogBox(context);
        alertDialogBox.setAlertOpend(false);
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(BluetoothScan.this.BLUETOOTH_SERVICE);
        this.BTAdapter = bluetoothManager.getAdapter();

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(receiver, intentFilter);

    }

    public void unregister() {
        Log.e(TAG,"unregister:: "+isFinished);
        context.unregisterReceiver(receiver);
    }


    //multi thrreading

    @Override
    public void run() {
        while(true) {
            try {
                Log.e(TAG,"Thread is running: :: isFinished :: "+isFinished);
                if(isFinished && !alertDialogBox.isAlertOpend()) {
                    BTAdapter.startDiscovery();
                    isFinished = false;
                }
                Log.e(TAG,"BTAdapter :: stop:");
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





    BroadcastReceiver receiver = new BroadcastReceiver(){

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReciever: "+isFinished);
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                float d = 10 ^ ((-69 - rssi)/20);
                Log.e(TAG, "name: "+device.getName()+" Address: "+device.getAddress()+" device : RSSI: "+rssi);
                boolean isTrusted = dbconnection.getDevice(device.getAddress());
                if (isTrusted && !alertDialogBox.isAlertOpend()) {
                    alertDialogBox.setAlertOpend(true);
                    alertDialogBox.showTrustedDeviceAlertBox(device.getName(), device.getAddress(), dbconnection);
                }
                //ArrayList<DeviceList> deviceList = dbconnection.getData();
//                for (DeviceList trustedeDevice: deviceList) {
//                    if (!alertDialogBox.isAlertOpend()) {
//                        Log.e(TAG, "devicemac : " + trustedeDevice.getMacAddress());
//                        if (trustedeDevice.getMacAddress().equals(device.getAddress())) {
//                            Log.e(TAG, "trusted device");
//                        } else {
//                            Log.e(TAG, "alertbox open");
//
//                            alertDialogBox.setAlertOpend(true);
//                            alertDialogBox.showTrustedDeviceAlertBox(device.getName(), device.getAddress(), dbconnection);
//
//                            Log.e(TAG, "alertbox close");
//                            // boolean isSucess = dbconnection.insertData(device.getAddress(), device.getName(),1);
//                        }
//                    }
//                }


//                if (deviceList.size() == 0) {
//                    alertDialogBox.setAlertOpend(true);
//                    alertDialogBox.showTrustedDeviceAlertBox(device.getName(), device.getAddress(), dbconnection);
//                }
                Log.e(TAG, " type: "+ device.getAddress().getClass());
            } else {
                Log.e(TAG,"else:: "+isFinished);
                isFinished = true;
            }

            // When discovery cycle finished
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.e(TAG,"Scannning finished");
                isFinished = true;
                if (foundDevices == null || foundDevices.isEmpty()) {
                    //Toast.makeText(MainActivity.this, "No Devices", Toast.LENGTH_LONG).show();
                }
            }
        }

    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e(TAG,"unregister:: onDestroy:: "+isFinished);
        context.unregisterReceiver(receiver);
    }

}
