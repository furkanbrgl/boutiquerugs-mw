package com.boutiquerugsmw.service;

import com.boutiquerugsmw.doa.ScheduledTestsDao;
import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.PropertyNames;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class ScheduledTestsService {

    private static final Logger logger = Logger.getLogger(ScheduledTestsService.class);

    @Autowired
    private ScheduledTestsDao scheduledTestsDao;

    @Value(PropertyNames.SCHEDULED_TESTS_ENVIRONMENT)
    private String scheduledTestsEnvironment;

    @Value(PropertyNames.SCHEDULED_TESTS_PROJECT_PATH)
    private String scheduledTestsProjectPath;

    @Async
    public void startTest() throws MessagingException {

        logger.info("_____________________________");
        logger.info("_____________________________");

        String[] O = this.generateMavenCommand(scheduledTestsDao.getScheduledTests(), new SeleniumInstanceModel());
        logger.info(O[2].toString());
        logger.info("_____________________________");
        logger.info("_____________________________");


    }

    private String[] generateMavenCommand(ScheduledTestModel scheduledTestModel, SeleniumInstanceModel seleniumInstance) throws MessagingException {

        StringBuilder cmd = new StringBuilder()
                .append("cd").append(" ")
                .append(this.scheduledTestsProjectPath)
                .append("&&")
                .append("dir")
                .append("&&")
                .append("mvn")
                .append(" ")
                .append("-B")
                .append(" ")
                .append("-Dtest=")
                .append(scheduledTestModel.getTestClassName())
                .append(" ")
                .append("-Dtestid=")
                .append(scheduledTestModel.getId())
                .append(" ")
//                .append("-Dserverip=")
//                .append(seleniumInstance.getIpAddress())
                .append(" ")
                .append("test");

        for (String key : scheduledTestModel.getTestParams().keySet()) {
            cmd.append(" -D").append(key).append("=\"").append(scheduledTestModel.getTestParams().get(key)).append("\"");
        }
        cmd.append(" -Denv=\"").append(this.scheduledTestsEnvironment).append("\"");


        return new String[]{"cmd.exe", "/c", cmd.toString()};
    }


}
