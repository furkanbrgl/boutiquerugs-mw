package com.boutiquerugsmw.repository.impl;

import com.boutiquerugsmw.model.MailContent;
import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.repository.ScheduledTestDao;
import com.boutiquerugsmw.util.Constants;
import com.boutiquerugsmw.util.PropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * parameters need to be taken from DB.
 */
@Repository
public class ScheduledTestsDaoImpl implements ScheduledTestDao {

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

        mongoTemplate.save(scheduledTestModel);

        return scheduledTestModel;
    }

    @Override
    public void updateScheduledTestStatus(String scenarioStatus, ScheduledTestModel scheduledTestModel) {

        scheduledTestModel.setTestStatus(scenarioStatus);
        mongoTemplate.save(scheduledTestModel);

    }

    @Override
    public void updateScheduledTestDetail(MailContent mailContent, ScheduledTestModel scheduledTestModel) {
        scheduledTestModel.setMailContent(mailContent);
        scheduledTestModel.setTestFinishTime(System.currentTimeMillis());
        mongoTemplate.save(scheduledTestModel);
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
