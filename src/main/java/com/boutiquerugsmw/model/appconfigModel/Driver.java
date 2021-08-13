package com.boutiquerugsmw.model.appconfig;

public class Driver {

    private String chrome;
    private String safari;

    @Override
    public String toString() {
        return "Driver{" +
                "chrome='" + chrome + '\'' +
                ", safari='" + safari + '\'' +
                '}';
    }

    public String getChrome() {
        return chrome;
    }

    public void setChrome(String chrome) {
        this.chrome = chrome;
    }

    public String getSafari() {
        return safari;
    }

    public void setSafari(String safari) {
        this.safari = safari;
    }
}
