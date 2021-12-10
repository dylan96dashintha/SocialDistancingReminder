package com.example.socialdistancingreminder.Model.DBConnection;

public class DeviceList {
    private String macAddress;
    private String deviceName;
    private String id;
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return id;
    }

    public void setDeviceId(String id) {
        this.id = id;
    }

}
