package com.boutiquerugsmw.model;

public class StatsDetail {


    private int completedTest;
    private int waitingTest;
    private int failedTest;
    private int runningTest;
    private int totalTestAmount;
    private int unCompletedTest;

    @Override
    public String toString() {
        return "StatsDetail{" +
                "completedTest=" + completedTest +
                ", waitingTest=" + waitingTest +
                ", failedTest=" + failedTest +
                ", runningTest=" + runningTest +
                ", totalTestAmount=" + totalTestAmount +
                ", uncompletedTest=" + unCompletedTest +
                '}';
    }

    public int getCompletedTest() {
        return completedTest;
    }

    public void setCompletedTest(int completedTest) {
        this.completedTest = completedTest;
    }

    public int getWaitingTest() {
        return waitingTest;
    }

    public void setWaitingTest(int waitingTest) {
        this.waitingTest = waitingTest;
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

    public int getTotalTestAmount() {
        return totalTestAmount;
    }

    public void setTotalTestAmount(int totalTestAmount) {
        this.totalTestAmount = totalTestAmount;
    }

    public int getUnCompletedTest() {
        return unCompletedTest;
    }

    public void setUnCompletedTest(int unCompletedTest) {
        this.unCompletedTest = unCompletedTest;
    }
}
