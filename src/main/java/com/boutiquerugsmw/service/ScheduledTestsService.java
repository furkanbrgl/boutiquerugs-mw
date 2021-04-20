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
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void startTest(ScheduledTestModel scheduledTestModel, SeleniumInstanceModel seleniumInstance) throws MessagingException {

        StringBuilder mavenLog = new StringBuilder();
        StringBuilder message = new StringBuilder();
        BufferedReader br = null;
        String mavenOutputLine;
        int numTestRun = -1;
        int numFailure = 0;
        int numError = 0;
        boolean exceptionOutput = false;
        mavenLog.append("<html><head></head><body>");
        try {

//            this.scheduledTestsDao.updateScheduledTestStatus(Constants.SCENARIO_STATUS_RUNNING, scheduledTestModel.getId());
//            this.scheduledTestsDao.updateScheduledTestStartTime(scheduledTestModel.getId());
//            this.scheduledTestsDao.updateScheduledTestHostId(scheduledTestModel.getId(), seleniumInstance.getHostId());

            logger.info("Test start info mail sending to  = "+ scheduledTestModel.getSchedulingUserEMail());
            //Send an e-mail
            logger.info("Maven command will run .. ");

            Process p = Runtime.getRuntime().exec(this.generateMavenCommand(scheduledTestModel, seleniumInstance));
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((mavenOutputLine = br.readLine()) != null) {
                mavenLog.append(mavenOutputLine).append("<br/>");

                if (mavenOutputLine.startsWith("Tests run:")) {
                    String regex = "Tests run: ([0-9]*), Failures: ([0-9]*), Errors: ([0-9]*), Skipped: ([0-9]*)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(mavenOutputLine);

                    while (matcher.find()) {
                        numTestRun = Integer.valueOf(matcher.group(1));
                        numFailure = Integer.valueOf(matcher.group(2));
                        numError = Integer.valueOf(matcher.group(3));
                    }
                }

                if (StringUtils.isEmpty(mavenOutputLine)) {
                    exceptionOutput = false;
                }

                if (exceptionOutput) {
                    message = this.parseTestMessage(message, mavenOutputLine);
                }

                if (mavenOutputLine.startsWith("Failed tests:")) {
                    exceptionOutput = true;
                    message = this.parseTestMessage(message, mavenOutputLine.substring(13));
                }

                if (mavenOutputLine.startsWith("Tests in error:")) {
                    exceptionOutput = true;
                    message = this.parseTestMessage(message, mavenOutputLine.substring(15));
                }
            }

            mavenLog.append("<br/>");
            mavenLog.append(message.toString());
            mavenLog.append("<br/></body></html>");

            if (numTestRun == 0 || numTestRun == -1 || numError > 0 || numFailure > 0) {
//                this.scheduledTestsDao.updateScheduledTestStatus(Constants.SCENARIO_STATUS_FAILED, scheduledTestModel.getId());
            } else {
//                this.scheduledTestsDao.updateScheduledTestStatus(Constants.SCENARIO_STATUS_COMPLETED, scheduledTestModel.getId());
            }

//          this.scheduledTestsDao.updateScheduledTestEndTime(scheduledTestModel.getId());
//          this.scheduledTestsDao.insertScheduledTestLog(scheduledTestModel.getId(), mavenLog.toString(), this.getHtmlLogUrl(scheduledTestModel.getId()), this.getReportUrl(scheduledTestModel.getId()));

            p.waitFor();

        } catch (Exception e) {

            logger.error(e.getMessage(), e);

        } finally {


            logger.info(mavenLog.toString());

            seleniumInstance.setAvailable(true);
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
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


    private StringBuilder parseTestMessage(StringBuilder message, String line) {
        try {
            if (!StringUtils.isEmpty(line)) {
                int i = line.indexOf(":");
                if (i > -1) {
                    message.append(line.substring(i + 1) + '\n');
                }
            }
        } catch (Exception ignored) {
        }
        return message;
    }
}
