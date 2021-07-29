package com.boutiquerugsmw.model;

public class SeleniumInstanceModel {

    private String ipAddress;
    private String port;
    private String nodeTag;
    private String hostId;
    private boolean available;

    public SeleniumInstanceModel(String ipAddress, String port, String nodeTag, String hostId, boolean available) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.nodeTag = nodeTag;
        this.hostId = hostId;
        this.available = available;
    }

    public SeleniumInstanceModel() {
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}
