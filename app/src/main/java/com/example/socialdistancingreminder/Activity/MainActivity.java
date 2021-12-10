package com.example.socialdistancingreminder.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.socialdistancingreminder.Model.BluetoothScan.BluetoothScan;
import com.example.socialdistancingreminder.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public BluetoothScan blueScan;
    Button pulicPlaceButton;
    Button trustedPlaceButton;
    Button getStat;
    Button getTrustedDevices;
    ImageView img;
    protected Thread thread;

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
        img = (ImageView) findViewById(R.id.imageView);
        blueScan = new BluetoothScan(MainActivity.this, img);


// ----------TRUSTED DEVICES------------
        getTrustedDevices= (Button) findViewById(R.id.trustedDevices);
        getTrustedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrustedDevices();
            }
        });

//-------------REST------------------

        pulicPlaceButton = (Button) findViewById(R.id.publicPlaceBtn);

        pulicPlaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //blueScan = new BluetoothScan(MainActivity.this);
                //thread = new Thread(blueScan);
                //thread.start();
                blueScan.initializeThread();
                blueScan.startThread();
                // Log.e(TAG,"isAlive Thread : "+thread.currentThread().isAlive());


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

        // trusted place: stop bluetooth scanning
        trustedPlaceButton = (Button) findViewById(R.id.trustPlaceBtn);

        trustedPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueScan.stopThread();
            }
        });


    }

    protected void onDestroy() {

        super.onDestroy();
        blueScan.unregister();
    }

    public void openNewActivity(Class javaClass){
        Intent intent = new Intent(this, javaClass);
        startActivity(intent);
    }
    public void openTrustedDevices(){
        Intent intent = new Intent(this, testActivity2.class);
        startActivity(intent);

    }
}