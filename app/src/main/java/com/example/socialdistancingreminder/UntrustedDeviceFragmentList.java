package com.example.socialdistancingreminder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialdistancingreminder.Model.DBConnection.DBconnection;
import com.example.socialdistancingreminder.Model.DBConnection.DeviceList;
import com.example.socialdistancingreminder.placeholder.PlaceholderContent;

import java.util.ArrayList;


public class UntrustedDeviceFragmentList extends Fragment {


    DBconnection dBconnection;
    ArrayList<DeviceList> deviceList2;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;

    public UntrustedDeviceFragmentList() {
    }


    public static UntrustedDeviceFragmentList newInstance(int columnCount) {
        UntrustedDeviceFragmentList fragment = new UntrustedDeviceFragmentList();
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
        View view = inflater.inflate(R.layout.fragment_untrusted_device_list_list, container, false);

        Context context1 = container.getContext();
        dBconnection= new DBconnection(context1);
        deviceList2  = dBconnection.getUntrustedData();


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new UntrustedDeviceRecyclerViewAdapter(deviceList2));
        }
        return view;
    }
    public void refresh(){

    }

}