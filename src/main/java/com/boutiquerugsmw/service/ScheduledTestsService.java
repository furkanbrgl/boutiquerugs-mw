package com.boutiquerugsmw.service;

import com.boutiquerugsmw.errors.ApiException;
import com.boutiquerugsmw.errors.ErrorType;
import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTestsService {

    private static final Logger LOGGER = LogManager.getLogger(ScheduledTestsService.class);

    @Autowired
    ScheduledTestsDaoImpl scheduledTestsRepository;


    public Page<ScheduledTestModel> getAllScheduledTests(Pageable pageable) {
        LOGGER.debug("Getting all scheduled tests for page size: " + pageable.getPageSize() +
                ", page number: " + pageable.getPageNumber()+ ", sort: " + pageable.getSort().toString());
        return scheduledTestsRepository.findAll(pageable);
    }

    public List<ScheduledTestModel> getLastNRecord(int LastNRecod) {
        LOGGER.debug("Getting the scheduled last N test by DESC: ");
        return scheduledTestsRepository.getLastNRecord(LastNRecod).orElseThrow(() ->  {
            return new ApiException(ErrorType.TEST_NOT_FOUND, "");
        });
    }


    public ScheduledTestModel getScheduledTestById(long testId) {
        LOGGER.debug("Getting the scheduled test by id: " + testId);
        return scheduledTestsRepository.findById(testId).orElseThrow(() ->  {
            LOGGER.warn("test id: " + testId + " not found!");
            return new ApiException(ErrorType.TEST_NOT_FOUND, String.valueOf(testId));
        });
    }

}
