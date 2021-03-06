package com.boutiquerugsmw.scheduler;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import com.boutiquerugsmw.service.ScheduledTestsStarter;
import com.boutiquerugsmw.util.ApplicationConfigProp;
import com.boutiquerugsmw.util.BrNodeMaps;
import com.boutiquerugsmw.util.BrNodeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;



@Component
public class LoginTestScheduler {

    private static final Logger log = LoggerFactory.getLogger(LoginTestScheduler.class);

    @Autowired
    private BrNodeMaps brNodeMaps;

    @Autowired
    private BrNodeStatus brNodeStatus;

    @Autowired
    ScheduledTestsStarter scheduledTestsStarter;

    @Autowired
    ScheduledTestsDaoImpl scheduledTestsRepository;

    @Autowired
    ApplicationConfigProp applicationConfigProp;

    @Scheduled(fixedDelay = 10000, initialDelay=5000)
    public void initiateLoginTest() throws MessagingException {
        try {
            scheduledTestsStarter.startTest(this.getScheduledTest());
        } catch (Exception e) {
        }
    }


    private SeleniumInstanceModel getAvailableSeleniumInstance(long testId) throws Exception {

        for (Map.Entry<String,SeleniumInstanceModel> SIntanceMap : brNodeMaps.getSeleniumInstancesMap().entrySet())
        {
            if(SIntanceMap.getValue().isReachable() && SIntanceMap.getValue().isAvailable()){
                if(brNodeStatus.isNodeReachable(SIntanceMap.getValue().getIpAddress())){
                    log.info("Available Node :::: Key = " + SIntanceMap.getKey() +", Value = " + SIntanceMap.getValue().toString());
                    SIntanceMap.getValue().setReachable(true);
                    SIntanceMap.getValue().setAvailable(false);
                    SIntanceMap.getValue().setRunningTestId(testId);
                    return SIntanceMap.getValue();
                }
            }
        }
        throw new Exception("Available Node Could Not Be Found :::");
    }

    private ScheduledTestModel getScheduledTest() throws Exception {

        long testId = System.currentTimeMillis() * 10000;

        return scheduledTestsRepository.getScheduledTests(testId,
                applicationConfigProp.getScenarios().getLoginTest(),
                this.getAvailableSeleniumInstance(testId),
                this.getScheduledTestParams(testId));
    }

    private Map<String, String> getScheduledTestParams(long testID ) {

        Map <String, String> result = new HashMap<String, String>();
        result.put("boutiqueRugsUserEmail", applicationConfigProp.getBoutiqueRugsUser().getEmail());
        result.put("boutiqueRugsUserPassword", applicationConfigProp.getBoutiqueRugsUser().getPassword());
        //TODO chorome diver paremeter is not supposed to be here. it does not relate to test params. !!
        result.put("chromeDriver", applicationConfigProp.getSelenium().getDriver().getChrome());
        result.put("testResultEmailAddress", applicationConfigProp.getScheduledTest().getResultEmailAddress());

        return result;
    }
}
