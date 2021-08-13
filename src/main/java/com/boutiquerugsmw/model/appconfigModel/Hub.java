package com.boutiquerugsmw.model.appconfigModel;

public class Hub {

    private String ipAddress;

    @Override
    public String toString() {
        return "Hub{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
