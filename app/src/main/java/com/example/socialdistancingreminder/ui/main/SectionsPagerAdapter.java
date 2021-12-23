package com.example.socialdistancingreminder.ui.main;

import android.content.Context;
import android.content.Intent;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.socialdistancingreminder.R;
import com.example.socialdistancingreminder.TrustedDeviceFragmentList;
import com.example.socialdistancingreminder.UntrustedDeviceFragmentList;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;


    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TrustedDeviceFragmentList();
                break;
            case 1:
                fragment = new UntrustedDeviceFragmentList();
                break;

        }
        return fragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}