package com.boutiquerugsmw.model.appconfigModel;

public class Hub {

    private String ipAddress;
    private String port;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Hub{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
