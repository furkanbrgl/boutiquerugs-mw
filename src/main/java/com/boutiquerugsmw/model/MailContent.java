package com.boutiquerugsmw.model;

public class MailContent {

    private long testID;
    private long nodeId;
    private String nodeName;

    private String testStartTime;
    private String testFinishTime;
    private String testDuration;
    private String testResult;
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

    public String getTestResult() {
        return testResult;
    }

    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }

    public long getTestStartMillis() {
        return testStartMillis;
    }

    public void setTestStartMillis(long testStartMillis) {
        this.testStartMillis = testStartMillis;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getCustomResult() {
        return customResult;
    }

    public void setCustomResult(String customResult) {
        this.customResult = customResult;
    }

    /**
     * TODO: will be developed as HTML generator page. furkan
     * @return
     */
    public String getHTMLContent(ScheduledTestModel scheduledTestModel) {

        String c = "Test ID : " + this.getTestID() + System.lineSeparator() +
                "Selenium Node ID : " + scheduledTestModel.getNodeId() + System.lineSeparator() +
                "Selenium Node Name : " + scheduledTestModel.getNodeName() + System.lineSeparator() +
                "Test Start Time : " + this.getTestStartTime() + System.lineSeparator() +
                "Test Finish Time : " + this.getTestFinishTime() + System.lineSeparator() +
                "Test Duration : " + this.getTestDuration() + System.lineSeparator() +
                "Test Result Status : " + this.getTestResult() + System.lineSeparator() +
                System.lineSeparator() + System.lineSeparator() +
                "Test Parameters : " + this.getCustomResult() + System.lineSeparator();
        return c;
    }
}