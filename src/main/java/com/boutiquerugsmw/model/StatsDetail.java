package com.boutiquerugsmw.model;

public class StatsDetail {


    private int completedTest;
    private int successfulTest;
    private int failedTest;
    private int runningTest;

    @Override
    public String toString() {
        return "StatsDetail{" +
                "completedTest=" + completedTest +
                ", successfulTest=" + successfulTest +
                ", failedTest=" + failedTest +
                ", runningTest=" + runningTest +
                '}';
    }

    public int getCompletedTest() {
        return completedTest;
    }

    public void setCompletedTest(int completedTest) {
        this.completedTest = completedTest;
    }

    public int getSuccessfulTest() {
        return successfulTest;
    }

    public void setSuccessfulTest(int successfulTest) {
        this.successfulTest = successfulTest;
    }

    public int getFailedTest() {
        return failedTest;
    }

    public void setFailedTest(int failedTest) {
        this.failedTest = failedTest;
    }

    public int getRunningTest() {
        return runningTest;
    }

    public void setRunningTest(int runningTest) {
        this.runningTest = runningTest;
    }
}
