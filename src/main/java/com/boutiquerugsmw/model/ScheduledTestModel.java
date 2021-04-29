package com.boutiquerugsmw.model;

import java.util.Map;

public class ScheduledTestModel {

    private long id;
    private long testId;
    private long nodeId;
    private String nodeName;
    private String testClassName;
    private String testResultEmailAddress;
    private Map<String, String> testParams;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
