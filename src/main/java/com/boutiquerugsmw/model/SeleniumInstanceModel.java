package com.boutiquerugsmw.model;

public class SeleniumInstanceModel {

    private String ipAddress;
    private long runningTestId;
    private String port;
    private String nodeTag;
    private String hostId;
    private boolean available;
    private boolean reachable;

    public SeleniumInstanceModel(String ipAddress, long runningTestId, String port, String nodeTag, String hostId, boolean available, boolean reachable) {
        this.ipAddress = ipAddress;
        this.runningTestId = runningTestId;
        this.port = port;
        this.nodeTag = nodeTag;
        this.hostId = hostId;
        this.available = available;
        this.reachable = reachable;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
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

    public String getNodeTag() {
        return nodeTag;
    }

    public void setNodeTag(String nodeTag) {
        this.nodeTag = nodeTag;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getRunningTestId() {
        return runningTestId;
    }

    public void setRunningTestId(long runningTestId) {
        this.runningTestId = runningTestId;
    }

    @Override
    public String toString() {
        return "SeleniumInstanceModel{" +
                "ipAddress='" + ipAddress + '\'' +
                ", runningTestId=" + runningTestId +
                ", port='" + port + '\'' +
                ", nodeTag='" + nodeTag + '\'' +
                ", hostId='" + hostId + '\'' +
                ", available=" + available +
                ", reachable=" + reachable +
                '}';
    }
}
