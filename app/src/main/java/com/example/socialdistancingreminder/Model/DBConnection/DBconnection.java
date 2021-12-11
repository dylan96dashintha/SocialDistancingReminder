package com.example.socialdistancingreminder.Model.DBConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBconnection extends SQLiteOpenHelper {
    private static final String TAG = "DBConnection";
    private static final String TABLE_NAME = "tursted_device_table";
    private static final String COL1 = "id";
    private static final String COL2 = "mac_address";
    private static final String COL3 = "device_name";
    private static String COL4 = "isTrusted";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" VARCHAR(255) ,"+ COL3+" VARCHAR(225) ,"+ COL4+" varchar(2))";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public DBconnection(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {
            Log.e(TAG,""+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.e(TAG,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Log.e(TAG,""+e);
        }

    }

    public boolean insertData(String mac_address, String device_name, String isTrusted)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, mac_address);
        contentValues.put(COL3, device_name);
        contentValues.put(COL4, isTrusted);
        Log.d(TAG, "addData: Adding: mac Address: "+ mac_address +" device name:"+ device_name+" isTrusted:"+ isTrusted+" to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null , contentValues);
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<DeviceList> getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL1,COL2,COL3, COL4};
        Cursor cursor =db.query(TABLE_NAME,columns,"isTrusted = 1", null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        ArrayList<DeviceList> deviceArrayList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            DeviceList deviceList = new DeviceList();
            String device_id =cursor.getString(cursor.getColumnIndex(COL1));
            String mac_address =cursor.getString(cursor.getColumnIndex(COL2));
            String  device_name =cursor.getString(cursor.getColumnIndex(COL3));
            deviceList.setDeviceId(device_id);
            deviceList.setMacAddress(mac_address);
            deviceList.setDeviceName(device_name);
            deviceArrayList.add(deviceList);
        }
        return deviceArrayList;
    }

    public boolean getDevice(String macAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL1,COL2,COL3, COL4};
        Log.d(TAG, "getDevice: getDevice: mac Address: "+ macAddress);
        Cursor cursor =db.query(TABLE_NAME,columns,"mac_address"+  "=\"" + macAddress + "\"", null,null,null,null,null);
        Log.d(TAG, "getDevice: cursor_info: cursor: "+ cursor.getCount());
        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getUntrustedDevice(String macAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL1,COL2,COL3, COL4};
        Log.d(TAG, "getDevice: getDevice: mac Address: "+ macAddress);
        Cursor cursor =db.query(TABLE_NAME,columns, "mac_address"+  "=\"" + macAddress + "\""+ " AND " + "isTrusted = 0" , null,null,null,null,null);
        Log.d(TAG, "getDevice: cursor_info: cursorUntrusted: "+ cursor.getCount());
        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean moveToUntrustedDevices(String deviceID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL4,"0");

        long result = db.update(TABLE_NAME, contentValues,"id = ?",new String[]{deviceID});
        Log.d(TAG, "Delete:: id: "+result);


        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }
}
