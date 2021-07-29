package com.boutiquerugsmw.core;

import com.boutiquerugsmw.util.SeleniumInstanceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTests {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTests.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private SeleniumInstanceConfig seleniumInstanceConfig;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info(seleniumInstanceConfig.getSeleniumInstanceMap().toString());
    }

}
