package com.example.socialdistancingreminder.Model.TrustedDeviceModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.R;

import java.util.ArrayList;

public class TrustedDeviceList extends AppCompatActivity {
    private static final String TAG = "TrustedDeviceList";
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_trusted_deivce_list);

        listView = findViewById(R.id.listview1);

        DBconnection dBconnection = new DBconnection(this);
        ArrayList<DeviceList> deviceList1 = dBconnection.getData();

//        MyAdapter adapter = new MyAdapter(this, deviceList1);

        listView.setAdapter(new MyAdapter(this,deviceList1));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(TrustedDeviceList.this, "Facebook Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 0) {
                    Toast.makeText(TrustedDeviceList.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 0) {
                    Toast.makeText(TrustedDeviceList.this, "Twitter Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 0) {
                    Toast.makeText(TrustedDeviceList.this, "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 0) {
                    Toast.makeText(TrustedDeviceList.this, "Youtube Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<DeviceList> deviceList;

        MyAdapter(Context c, ArrayList<DeviceList> deviceList) {
            super(c, R.layout.row, R.id.deviceName1);
            this.context = c;
            this.deviceList = deviceList;

        }

        @NonNull
        @Override
        public int getCount() {
            return this.deviceList.size();
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);

            TextView deviceName2 = row.findViewById(R.id.deviceName1);
            TextView macAddress2 = row.findViewById(R.id.macAddress1);

            // now set our resources on views

//            deviceName1.setText(deviceList1.get(position).getDeviceName());

            deviceName2.setText(deviceList.get(position).getDeviceName());
            macAddress2.setText(deviceList.get(position).getMacAddress());

            return row;
//        }
        }

    }
}