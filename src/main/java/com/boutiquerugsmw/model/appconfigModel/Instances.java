package com.boutiquerugsmw.model.appconfigModel;

import java.util.Map;

public class Instances {

    private String port;
    private Map<String, String> ipAddresses;

    @Override
    public String toString() {
        return "Instances{" +
                "port='" + port + '\'' +
                ", ipAddresses=" + ipAddresses +
                '}';
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Map<String, String> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(Map<String, String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }
}
