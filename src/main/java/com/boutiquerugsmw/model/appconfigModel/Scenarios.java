package com.boutiquerugsmw.model.appconfig;

public class Scenarios {

    private String loginTest;
    private String paymentTest;

    @Override
    public String toString() {
        return "Scenarios{" +
                "loginTest='" + loginTest + '\'' +
                ", paymentTest='" + paymentTest + '\'' +
                '}';
    }

    public String getLoginTest() {
        return loginTest;
    }

    public void setLoginTest(String loginTest) {
        this.loginTest = loginTest;
    }

    public String getPaymentTest() {
        return paymentTest;
    }

    public void setPaymentTest(String paymentTest) {
        this.paymentTest = paymentTest;
    }
}
