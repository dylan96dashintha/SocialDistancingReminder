package com.example.socialdistancingreminder.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.R;
import com.example.socialdistancingreminder.TrustedDeviceList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<DeviceList> {


    public ListAdapter(Context context, ArrayList<DeviceList> deviceList) {
        super(context, R.layout.activity_trusted_deivce_list,deviceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DeviceList trustedDevice = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_trusted_deivce_list,parent,false);

        }

        TextView deviceName1 = convertView.findViewById(R.id.deviceName1);
        TextView macAddress1 = convertView.findViewById(R.id.macAddress1);

        deviceName1.setText(trustedDevice.getDeviceName());
        macAddress1.setText(trustedDevice.getMacAddress());



        return convertView;
    }
}
