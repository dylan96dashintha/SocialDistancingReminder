package com.example.socialdistancingreminder.Model.TrustedDeviceModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TrustedDeviceList extends AppCompatActivity {
    private static final String TAG = "TrustedDeviceList";
    ListView listView;
    DBconnection dBconnection;
    private static Toolbar toolbar;
    private static ViewPager viewPager;
    private static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_trusted_deivce_list);


        listView = findViewById(R.id.listview1);

        dBconnection= new DBconnection(this);
        ArrayList<DeviceList> deviceList1 = dBconnection.getData();



        listView.setAdapter(new MyAdapter(this,deviceList1));

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



            deviceName2.setText(deviceList.get(position).getDeviceName());
            macAddress2.setText(deviceList.get(position).getMacAddress());

            ///////////////////////////////ALERTTTTTTTTTTTTTTTTTTTTTTT///////////////////////////
            Button removeDevice= (Button) row.findViewById(R.id.deleteDevice);
            removeDevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Confirm moving "+"'"+deviceList.get(position).getDeviceName()+"'"+ " to Untrusted Devices?");
                    builder1.setCancelable(true);

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Boolean checkUntrusted = dBconnection.moveToUntrustedDevices(deviceList.get(position).getDeviceId());
                                    Toast.makeText(TrustedDeviceList.this, "Moved to Untrusted", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(getIntent());
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });
///////////////////////////////ALERTTTTTTTTTTTTTTTTTTTTTTT///////////////////////////
            return row;
        }

    }


}
