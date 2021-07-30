package com.boutiquerugsmw.model;

public class MailContent {

    private Object testID;
    private String nodeIpAddress;
    private String nodeName;

    private String hostID;
    private String nodePort;

    private String testStartTime;
    private String testFinishTime;
    private String testDuration;
    private String testResultStatus;
    private String testResultInfo;
    private String customResult;
    private long testStartMillis;

    public String getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public String getTestFinishTime() {
        return testFinishTime;
    }

    public void setTestFinishTime(String testFinishTime) {
        this.testFinishTime = testFinishTime;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public String getTestResultStatus() {
        return testResultStatus;
    }

    public Object getTestID() {
        return testID;
    }

    public void setTestID(Object testID) {
        this.testID = testID;
    }

    public long getTestStartMillis() {
        return testStartMillis;
    }

    public void setTestStartMillis(long testStartMillis) {
        this.testStartMillis = testStartMillis;
    }



    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setTestResultStatus(String testResultStatus) {
        this.testResultStatus = testResultStatus;
    }

    public String getCustomResult() {
        return customResult;
    }

    public void setCustomResult(String customResult) {
        this.customResult = customResult;
    }

    public String getTestResultInfo() {
        return testResultInfo;
    }

    public void setTestResultInfo(String testResultInfo) {
        this.testResultInfo = testResultInfo;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    public String getNodePort() {
        return nodePort;
    }

    public void setNodePort(String nodePort) {
        this.nodePort = nodePort;
    }

    public String getNodeIpAddress() {
        return nodeIpAddress;
    }

    public void setNodeIpAddress(String nodeIpAddress) {
        this.nodeIpAddress = nodeIpAddress;
    }

    /**
     * TODO: will be developed as HTML generator page. furkan
     * @return
     */
    public String getHTMLContent(ScheduledTestModel scheduledTestModel) {

        String c = "Test ID : " + this.getTestID() + System.lineSeparator() +
                "Selenium Node Ip : " + scheduledTestModel.getSeleniumInstanceModel().getIpAddress() + System.lineSeparator() +
                "Selenium Node Name : " + scheduledTestModel.getSeleniumInstanceModel().getNodeTag() + System.lineSeparator() +
                "Selenium Hub Ip : " + scheduledTestModel.getSeleniumInstanceModel().getHostId() + System.lineSeparator() +
                "Test Start Time : " + this.getTestStartTime() + System.lineSeparator() +
                "Test Finish Time : " + this.getTestFinishTime() + System.lineSeparator() +
                "Test Duration : " + this.getTestDuration() + System.lineSeparator() +
                System.lineSeparator() +
                "Test Add Info : " + this.getTestResultInfo() + System.lineSeparator() +
                "Test Result Status : " + this.getTestResultStatus() + System.lineSeparator() +
                System.lineSeparator() + System.lineSeparator() +
                "Test Parameters : " + this.getCustomResult() + System.lineSeparator();
        return c;
    }



}
