package com.boutiquerugsmw.repository;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduledTestsDao {

    public ScheduledTestModel getScheduledTests(long testId);

    void updateScheduledTestStatus(String scenarioStatusWaiting, ScheduledTestModel scheduledTestModel);

    void updateScheduledTestDetail(MailContent mailContent, ScheduledTestModel scheduledTestModel);

    Page<ScheduledTestModel> findAll(Pageable pageable);

    Object getLastNRecord(int LastNRecod);

    Optional<ScheduledTestModel> findById(long testId);
}
