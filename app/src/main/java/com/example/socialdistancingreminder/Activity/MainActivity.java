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
import android.widget.ImageView;

import com.example.socialdistancingreminder.DeviceTabs;
import com.example.socialdistancingreminder.Model.BluetoothScan.BluetoothScan;
import com.example.socialdistancingreminder.Model.SliderData.SliderAdapter;
import com.example.socialdistancingreminder.Model.SliderData.SliderData;
import com.example.socialdistancingreminder.Model.TrustedDeviceModel.TrustedDeviceList;
import com.example.socialdistancingreminder.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Slider urls
    String url1 = "https://www.health.nsw.gov.au/Infectious/covid-19/PublishingImages/wear-a-mask-t-sinhalese.png";
    String url2 = "https://www.health.nsw.gov.au/Infectious/covid-19/PublishingImages/Domestic-cleaning-social-TW-Sinhalese.jpg";
    String url3 = "https://www.health.nsw.gov.au/Infectious/covid-19/PublishingImages/no-visitors-sinhalese-tt.jpg";


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
        //slider logic
        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();
        //
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
                openNewActivity(DeviceTabs.class);
            }
        });

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
//    public void openTrustedDevices(){
//        Intent intent = new Intent(this, testActivity2.class);
//        startActivity(intent);
//
//    }
}