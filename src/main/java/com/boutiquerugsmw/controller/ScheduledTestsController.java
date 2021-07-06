package com.boutiquerugsmw.controller;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.service.ScheduledTestsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduledTests")
public class ScheduledTestsController {

    private static final Logger LOGGER = LogManager.getLogger(ScheduledTestsController.class);

    @Autowired
    private ScheduledTestsService scheduledTestsService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<ScheduledTestModel> getAllCampaigns(@RequestParam(value="page", required = false, defaultValue = "10") int page,
                                                    @RequestParam(value="size", required = false, defaultValue = "10") int size) {
        LOGGER.info("Entering in rest endpoint to get all scheculed Tests");
        PageRequest pageRequest = PageRequest.of(page, size);
        return scheduledTestsService.getAllScheduledTests(pageRequest);
    }

    @RequestMapping(value = "/{testId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ScheduledTestModel getCampaignById(@PathVariable("testId") long testId) {
        LOGGER.info("Entering in rest endpoint to get a sheculed test by id");
        return scheduledTestsService.getScheduledTestById(testId);
    }

    @RequestMapping(value = "/lastnrecord/{lastNRecord}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduledTestModel> getLastNRecord(@PathVariable("lastNRecord") int lastNRecord) {
        LOGGER.info("Entering in rest endpoint to get last N sheculed tests by DESC");
        return scheduledTestsService.getLastNRecord(lastNRecord);
    }

}
