package com.example.socialdistancingreminder.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<TrustedDevice> {

    public ListAdapter(Context context, ArrayList<TrustedDevice> TrustedDeviceArrayList) {

        super(context, R.layout.list_item, TrustedDeviceArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TrustedDevice trustedDevice = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }


        TextView deviceName = convertView.findViewById(R.id.deviceName);
        TextView macAddress = convertView.findViewById(R.id.macAddress);

        deviceName.setText(trustedDevice.name);
        macAddress.setText(trustedDevice.mac);



        return convertView;
    }
}
