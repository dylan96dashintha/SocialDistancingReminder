package com.example.socialdistancingreminder;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.Model.TrustedDeviceModel.TrustedDeviceList;
import com.example.socialdistancingreminder.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.socialdistancingreminder.databinding.FragmentTrustedDeviceListBinding;

import java.util.ArrayList;
import java.util.List;


public class MyTrustedDeviceRecyclerViewAdapter extends RecyclerView.Adapter<MyTrustedDeviceRecyclerViewAdapter.ViewHolder> {
    Context context;
    DBconnection dBconnection;
    private final ArrayList<DeviceList> deviceList1;

    public MyTrustedDeviceRecyclerViewAdapter(ArrayList<DeviceList> items) {

        deviceList1=items;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        dBconnection= new DBconnection(parent.getContext());


        return new ViewHolder(FragmentTrustedDeviceListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = deviceList1.get(position);

        holder.mIdView.setText(deviceList1.get(position).getDeviceName());
        holder.mContentView.setText(deviceList1.get(position).getMacAddress());


        ///////////////////////////////ALERTTTTTTTTTTTTTTTTTTTTTTT///////////////////////////

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(holder.mButtonView.getContext());
                builder1.setMessage("Confirm moving "+"'"+deviceList1.get(position).getDeviceName()+"'"+ " to Untrusted Devices?");
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

                                Boolean checkUntrusted = dBconnection.moveToUntrustedDevices(deviceList1.get(position).getDeviceId());

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
        return deviceList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mButtonView;
        public PlaceholderItem mItem;




        public ViewHolder(FragmentTrustedDeviceListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.deviceName1;
            mContentView = binding.macAddress1;
            mButtonView = binding.deleteDevice1;



        }



















        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}