package com.example.socialdistancingreminder;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.socialdistancingreminder.databinding.FragmentUntrustedDeviceListBinding;

import java.util.ArrayList;
import java.util.List;


public class UntrustedDeviceRecyclerViewAdapter extends RecyclerView.Adapter<UntrustedDeviceRecyclerViewAdapter.ViewHolder> {
    DBconnection dBconnection;
    Fragment fragment;
    private final ArrayList<DeviceList> deviceList2;

    public UntrustedDeviceRecyclerViewAdapter(ArrayList<DeviceList> items) {

        deviceList2=items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dBconnection= new DBconnection(parent.getContext());
        return new ViewHolder(FragmentUntrustedDeviceListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(deviceList2.get(position).getDeviceName());
        holder.mContentView.setText(deviceList2.get(position).getMacAddress());


        ///////////////////////////////ALERTTTTTTTTTTTTTTTTTTTTTTT///////////////////////////

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(holder.mButtonView.getContext());
                builder1.setMessage("Confirm Deleting "+"'"+deviceList2.get(position).getDeviceName());
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

                                Boolean checkUntrusted = dBconnection.deleteUntrustedDevices(deviceList2.get(position).getDeviceId());
                                fragment = new UntrustedDeviceFragmentList();
                                fragment.notify();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });


    }


///////////////////////////////ALERTTTTTTTTTTTTTTTTTTTTTTT///////////////////////////



    @Override
    public int getItemCount() {
        return deviceList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mButtonView;

        public ViewHolder(FragmentUntrustedDeviceListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.deviceName2;
            mContentView = binding.macAddress2;
            mButtonView = binding.deleteDevice2;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}