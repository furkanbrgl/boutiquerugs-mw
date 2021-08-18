package com.boutiquerugsmw.controller;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.TestStatsDTO;
import com.boutiquerugsmw.service.LoginTestService;
import com.boutiquerugsmw.service.ScheduledTestsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginTest")
public class LoginTestController  {

    private static final Logger LOGGER = LogManager.getLogger(LoginTestController.class);

    @Autowired
    private ScheduledTestsService scheduledTestsService;

    @Autowired
    private LoginTestService loginTestService;

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TestStatsDTO getLoginTestStats() {
        return loginTestService.getLoginTestStats();
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<ScheduledTestModel> getAllTests(@RequestParam(value="page", required = false, defaultValue = "10") int page,
                                                @RequestParam(value="size", required = false, defaultValue = "10") int size) {
        LOGGER.info("Entering in rest endpoint to get all scheculed Tests");
        PageRequest pageRequest = PageRequest.of(page, size);
        return loginTestService.getAllScheduledTests(pageRequest);
    }

    @RequestMapping(value = "/{testId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ScheduledTestModel getTestById(@PathVariable("testId") long testId) {
        LOGGER.info("Entering in rest endpoint to get a sheculed test by id");
        return scheduledTestsService.getScheduledTestById(testId);
    }

}
