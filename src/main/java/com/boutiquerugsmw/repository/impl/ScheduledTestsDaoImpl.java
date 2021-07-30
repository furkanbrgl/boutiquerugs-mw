package com.boutiquerugsmw.repository.impl;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.repository.ScheduledTestsDao;
import com.boutiquerugsmw.repository.ScheduledTestsRepository;
import com.boutiquerugsmw.util.Constants;
import com.boutiquerugsmw.util.PropertyNames;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * parameters need to be taken from DB. When they are set up from web.
 */
@Repository
public class ScheduledTestsDaoImpl implements ScheduledTestsDao {

    private static final Logger LOGGER = LogManager.getLogger(ScheduledTestsDaoImpl.class);

    @Value(PropertyNames.TEST_RESULT_EMAIL_ADDRESS)
    private String testResultEmailAddress;

    @Autowired
    private ScheduledTestsRepository scheduledTestsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * These paremeters must've been assigned on the website and able to be reached by testID on the MW.
     * @return
     */
    public ScheduledTestModel getScheduledTests(long testId, String testScenarioClassName, SeleniumInstanceModel availableSeleniumInstance, Map<String, String> scheduledTestParams) {

        ScheduledTestModel scheduledTestModel = new ScheduledTestModel();

        scheduledTestModel.setTestId(testId);
        scheduledTestModel.setTestStartTime(testId);
        scheduledTestModel.setSeleniumInstanceModel(availableSeleniumInstance);
        scheduledTestModel.setTestResultEmailAddress(testResultEmailAddress);
        scheduledTestModel.setScenarioClassName(testScenarioClassName);
        scheduledTestModel.setTestParams(scheduledTestParams);

        scheduledTestModel.setTestStatus(Constants.SCENARIO_STATUS_WAITING);

        scheduledTestsRepository.save(scheduledTestModel);

        return scheduledTestModel;
    }

    @Override
    public void updateScheduledTestStatus(String scenarioStatus, ScheduledTestModel scheduledTestModel) {

        scheduledTestModel.setTestStatus(scenarioStatus);
        scheduledTestsRepository.save(scheduledTestModel);

    }

    @Override
    public void updateScheduledTestDetail(MailContent mailContent, ScheduledTestModel scheduledTestModel) {
        scheduledTestModel.setMailContent(mailContent);
        scheduledTestModel.setTestFinishTime(System.currentTimeMillis());
        scheduledTestsRepository.save(scheduledTestModel);
    }

    @Override
    public Page<ScheduledTestModel> findAll(Pageable pageable) {
        return scheduledTestsRepository.findAll(pageable);
    }

    @Override
    public Optional<List<ScheduledTestModel>> getLastNRecord(int LastNRecod) {

        Query query = new Query();
        query.limit(LastNRecod);
        query.with(Sort.by(Sort.Direction.DESC,"testId"));

        return Optional.of(mongoTemplate.find(query, ScheduledTestModel.class));
    }

    @Override
    public Optional<ScheduledTestModel> findById(long testId) {
        return scheduledTestsRepository.findById(testId);
    }

}
