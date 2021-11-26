package com.example.socialdistancingreminder.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.R;

import java.util.ArrayList;

public class TrustedDevices extends AppCompatActivity {
    private static final String TAG = "TrustedDevices";
    DBconnection dbconnection = new DBconnection(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trusted_devices);
       showResult();
    }

    protected  void showResult() {
        ArrayList<DeviceList> deviceList = dbconnection.getData();
//        StringBuffer buffer = new StringBuffer();
//            for (DeviceList trustedDevice: deviceList) {
//                buffer.append("Device Name"+trustedDevice.getDeviceName()+"\n");
//                buffer.append("Mac Address"+trustedDevice.getMacAddress ()+"\n");
//
//                Log.e(TAG,"Thread is running: :: isFinished :: "+trustedDevice.getDeviceName());
//            }
        TextView theTextView = (TextView) findViewById(R.id.textView1);
        for (DeviceList trustedDevice: deviceList) {
            theTextView.setText(trustedDevice.getDeviceName()+" - " +trustedDevice.getMacAddress()+"\n");
        }

        Toast.makeText(getApplicationContext(), "Trusted Devices", Toast.LENGTH_SHORT).show();



    }

}
