package com.boutiquerugsmw.repository;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;

public interface ScheduledTestDao {

    public ScheduledTestModel getScheduledTests(long testId);

    void updateScheduledTestStatus(String scenarioStatusWaiting, ScheduledTestModel scheduledTestModel);

    void updateScheduledTestDetail(MailContent mailContent, ScheduledTestModel scheduledTestModel);
}
