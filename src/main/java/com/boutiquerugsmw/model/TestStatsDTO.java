package com.boutiquerugsmw.model;

public class TestStatsDTO {

    private String testName;
    private long updateTime;
    private StatsDetail statsDetail;

    @Override
    public String toString() {
        return "TestStatsDTO{" +
                "testName='" + testName + '\'' +
                ", updateTime=" + updateTime +
                ", statsDetail=" + statsDetail +
                '}';
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public StatsDetail getStatsDetail() {
        return statsDetail;
    }

    public void setStatsDetail(StatsDetail statsDetail) {
        this.statsDetail = statsDetail;
    }
}
