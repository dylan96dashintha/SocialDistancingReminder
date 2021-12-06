package com.example.socialdistancingreminder.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdistancingreminder.R;

public class TrustedDevice extends AppCompatActivity {
    String name,mac;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
    }


    public TrustedDevice(String name, String MAC) {
        this.name = name;
        this.mac = mac;
    }

}
