package com.boutiquerugsmw.repository.impl;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * parameters need to be taken from DB.
 */
@Repository
public class ScheduledTestsDaoImpl implements ScheduledTestsDao {

    private static final Logger LOGGER = LogManager.getLogger(ScheduledTestsDaoImpl.class);


    @Value(PropertyNames.BOUTIQUE_RUGS_USER_EMAIL)
    private String boutiqueRugsUserEmail;

    @Value(PropertyNames.BOUTIQUE_RUGS_PASSWORD)
    private String boutiqueRugsUserPassword;

    @Value(PropertyNames.CHROME_DRIVER)
    private String chromeDriver;

    @Value(PropertyNames.TEST_RESULT_EMAIL_ADDRESS)
    private String testResultEmailAddress;

    @Value(PropertyNames.TEST_SCENARIO_CLASS_NAME)
    private String testScenarioClassName;

    @Autowired
    private ScheduledTestsRepository scheduledTestsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * These paremeters must've been assigned on the website and able to be reached by testID on the MW.
     * @return
     */
    @Override
    public ScheduledTestModel getScheduledTests(long testId) {

        ScheduledTestModel scheduledTestModel = new ScheduledTestModel();

        scheduledTestModel.setTestId(testId);
        scheduledTestModel.setTestStartTime(testId);
        scheduledTestModel.setNodeId(999999);
        scheduledTestModel.setNodeName("999999NodeName");
        scheduledTestModel.setTestResultEmailAddress(testResultEmailAddress);
        scheduledTestModel.setScenarioClassName(testScenarioClassName);
        scheduledTestModel.setTestParams(this.getScheduledTestParams(scheduledTestModel.getTestId()));
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

    private Map<String, String> getScheduledTestParams(Object testID ) {

        Map <String, String> result = new HashMap<String, String>();
        result.put("boutiqueRugsUserEmail", boutiqueRugsUserEmail);
        result.put("boutiqueRugsUserPassword", boutiqueRugsUserPassword);
        result.put("chromeDriver", chromeDriver);
        result.put("testResultEmailAddress", testResultEmailAddress);

        return result;
    }


}