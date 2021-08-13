package com.boutiquerugsmw.model.appconfig;

public class Selenium {

    private Driver driver;
    private Instances instances;
    private Hub hub;

    @Override
    public String toString() {
        return "Selenium{" +
                "driver=" + driver +
                ", instances=" + instances +
                ", hub=" + hub +
                '}';
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }
}
