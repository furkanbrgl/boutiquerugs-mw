package com.boutiquerugsmw.model.appconfigModel;

public class ScheduledTest {

    private String environment;
    private String projectPath;
    private String logPath;
    private String reportPath;
    private String resultEmailAddress;
    private FromEmailAddress fromEmailAddress;

    @Override
    public String toString() {
        return "ScheduledTest{" +
                "environment='" + environment + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", logPath='" + logPath + '\'' +
                ", reportPath='" + reportPath + '\'' +
                ", resultEmailAddress='" + resultEmailAddress + '\'' +
                ", fromEmailAddress=" + fromEmailAddress +
                '}';
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getResultEmailAddress() {
        return resultEmailAddress;
    }

    public void setResultEmailAddress(String resultEmailAddress) {
        this.resultEmailAddress = resultEmailAddress;
    }

    public FromEmailAddress getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(FromEmailAddress fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }
}
