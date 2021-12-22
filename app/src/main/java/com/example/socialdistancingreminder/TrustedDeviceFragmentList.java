package com.example.socialdistancingreminder;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.Model.TrustedDeviceModel.TrustedDeviceList;
import com.example.socialdistancingreminder.placeholder.PlaceholderContent;
import android.view.LayoutInflater;
import java.util.ArrayList;


public class TrustedDeviceFragmentList extends Fragment {

    DBconnection dBconnection;
    ArrayList<DeviceList> deviceList1;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public TrustedDeviceFragmentList() {

    }


    public static TrustedDeviceFragmentList newInstance(int columnCount) {
        TrustedDeviceFragmentList fragment = new TrustedDeviceFragmentList();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trusted_device_list_list, container, false);

        Context context1 = container.getContext();
        dBconnection= new DBconnection(context1);
        deviceList1  = dBconnection.getData();


        // Set the adapter
        if (view instanceof RecyclerView) {

            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new MyTrustedDeviceRecyclerViewAdapter(deviceList1));
        }

        return view;
    }
}