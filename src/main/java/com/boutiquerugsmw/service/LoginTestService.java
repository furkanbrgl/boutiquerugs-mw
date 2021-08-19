package com.boutiquerugsmw.service;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.StatsDetail;
import com.boutiquerugsmw.model.TestStatsDTO;
import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import com.boutiquerugsmw.util.ApplicationConfigProp;
import com.boutiquerugsmw.util.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoginTestService {

    private static final Logger LOGGER = LogManager.getLogger(LoginTestService.class);

    @Autowired
    private ApplicationConfigProp applicationConfigProp;

    @Autowired
    ScheduledTestsDaoImpl scheduledTestsRepository;

    public Page<ScheduledTestModel> getAllScheduledTests(Pageable pageable) {
        LOGGER.debug("Getting all scheduled Login tests for page size: " + pageable.getPageSize() +
                ", page number: " + pageable.getPageNumber()+ ", sort: " + pageable.getSort().toString());
        return scheduledTestsRepository.findAll(pageable);
    }

    public TestStatsDTO getLoginTestStats() {

        int runningTest = scheduledTestsRepository.getTestAmountByTestNameAndTestStatus(applicationConfigProp.getScenarios().getLoginTest(), Constants.SCENARIO_STATUS_RUNNING);
        int waitingTest = scheduledTestsRepository.getTestAmountByTestNameAndTestStatus(applicationConfigProp.getScenarios().getLoginTest(), Constants.SCENARIO_STATUS_WAITING);
        int failedTest = scheduledTestsRepository.getTestAmountByTestNameAndTestStatus(applicationConfigProp.getScenarios().getLoginTest(), Constants.SCENARIO_STATUS_FAILED);
        int completedTest = scheduledTestsRepository.getTestAmountByTestNameAndTestStatus(applicationConfigProp.getScenarios().getLoginTest(), Constants.SCENARIO_STATUS_COMPLETED);
        int uncompletedTest = scheduledTestsRepository.getTestAmountByTestNameAndTestStatus(applicationConfigProp.getScenarios().getLoginTest(), Constants.SCENARIO_STATUS_UNCOMPLETED);

        int totalTest = runningTest + waitingTest + failedTest + completedTest + uncompletedTest;

        TestStatsDTO dto = new TestStatsDTO();
        StatsDetail detail = new StatsDetail();

        detail.setFailedTest(failedTest);
        detail.setRunningTest(runningTest);
        detail.setCompletedTest(completedTest);
        detail.setWaitingTest(waitingTest);
        detail.setTotalTestAmount(totalTest);
        detail.setUnCompletedTest(uncompletedTest);

        dto.setTestName(applicationConfigProp.getScenarios().getLoginTest());
        dto.setUpdateTime(System.currentTimeMillis());
        dto.setStatsDetail(detail);
        LOGGER.info("Stats are going through For " + applicationConfigProp.getScenarios().getLoginTest());

        return dto;
    }
}
