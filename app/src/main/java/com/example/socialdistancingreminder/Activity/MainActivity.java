package com.example.socialdistancingreminder.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.socialdistancingreminder.Model.BluetoothScan.BluetoothScan;
import com.example.socialdistancingreminder.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public BluetoothScan blueScan;
    Button pulicPlaceButton;
    Button getStat;
    Button trustedDevices;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if (permissionCheck != 0) {
            Log.e(TAG,"permission");
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
        }
        blueScan = new BluetoothScan(MainActivity.this);

        pulicPlaceButton = (Button) findViewById(R.id.publicPlaceBtn);

        pulicPlaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //blueScan = new BluetoothScan(MainActivity.this);
                Thread thread = new Thread(blueScan);
                thread.start();

                 Log.e(TAG,"isAlive Thread : "+thread.currentThread().isAlive());


                //blueScan.startScan();


            }
        });

//        get covid statistics
        getStat = (Button) findViewById(R.id.getStat);

        getStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(CovidStatisticActivity.class);

            }
        });
        trustedDevices = (Button) findViewById(R.id.trustedDevices);
        trustedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(TrustedDevices.class);

            }
        });


    }

    protected void onDestroy() {

        super.onDestroy();
        blueScan.unregister();
    }

    public void openNewActivity(Class abc){
        Intent intent = new Intent(this,abc );
        startActivity(intent);
    }


}