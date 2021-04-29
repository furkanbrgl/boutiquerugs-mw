package com.boutiquerugsmw.doa;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.util.PropertyNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * parameters need to be taken from DB.
 */
@Component
public class ScheduledTestsDao {

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


    public ScheduledTestModel getScheduledTests() {

        ScheduledTestModel scheduledTestModel = new ScheduledTestModel();

        scheduledTestModel.setId(System.currentTimeMillis());
        scheduledTestModel.setTestId(System.currentTimeMillis());
        scheduledTestModel.setNodeId(999999);
        scheduledTestModel.setNodeName("999999NodeName");
        scheduledTestModel.setTestResultEmailAddress(testResultEmailAddress);

        //comes from db by testId
        scheduledTestModel.setScenarioClassName(testScenarioClassName);

        scheduledTestModel.setTestParams(this.getScheduledTestParams(scheduledTestModel.getTestId()));

        return scheduledTestModel;
    }

    /**
     * Extra values come from db by testID. it'll happen here.
     */
    private Map<String, String> getScheduledTestParams(long testID ) {

        Map <String, String> result = new HashMap<String, String>();

        result.put("boutique.rugs.user.email", boutiqueRugsUserEmail);
        result.put("boutique.rugs.user.password", boutiqueRugsUserPassword);
        result.put("chrome.driver", chromeDriver);
        result.put("test.result.email.address", testResultEmailAddress);

        return result;
    }

}
