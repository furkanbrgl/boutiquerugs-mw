package com.boutiquerugsmw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class ScheduledTestModel {

    @Id
    private long testId;
    private long nodeId;
    private String nodeName;
    private String testClassName;
    private String testResultEmailAddress;
    private String testStatus;

    private long testStartTime;
    private long testFinishTime;

    private Map<String, String> testParams;
    private MailContent mailContent;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public void setScenarioClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public Map <String, String> getTestParams() {
        return testParams;
    }

    public void setTestParams(Map <String, String> testParams) {
        this.testParams = testParams;
    }

    public String getTestResultEmailAddress() {
        return testResultEmailAddress;
    }

    public void setTestResultEmailAddress(String testResultEmailAddress) {
        this.testResultEmailAddress = testResultEmailAddress;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public MailContent getMailContent() {
        return mailContent;
    }

    public void setMailContent(MailContent mailContent) {
        this.mailContent = mailContent;
    }

    public long getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }

    public long getTestFinishTime() {
        return testFinishTime;
    }

    public void setTestFinishTime(long testFinishTime) {
        this.testFinishTime = testFinishTime;
    }
}
