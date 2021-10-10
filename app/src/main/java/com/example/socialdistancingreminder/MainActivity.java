package com.example.socialdistancingreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button pulicPlaceButton;
    Button getStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothScan blueScan = new BluetoothScan();

        pulicPlaceButton = (Button) findViewById(R.id.publicPlaceBtn);

        pulicPlaceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isTrust = blueScan.scanDevice();
            }
        });

//        get covid statistics
        getStat = (Button) findViewById(R.id.getStat);

        getStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();

            }
        });



    }

    public void openNewActivity(){
        Intent intent = new Intent(this, CovidStatisticActivity.class);
        startActivity(intent);
    }
}