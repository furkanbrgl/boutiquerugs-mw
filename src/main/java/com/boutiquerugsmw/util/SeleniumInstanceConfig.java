package com.boutiquerugsmw.util;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SeleniumInstanceConfig {

    private static final Logger log = LoggerFactory.getLogger(SeleniumInstanceConfig.class);

    private Map<String, SeleniumInstanceModel> seleniumInstanceMap = new ConcurrentHashMap<>();

    public Map<String, SeleniumInstanceModel> getSeleniumInstanceMap() {
        return seleniumInstanceMap;
    }

    @Override
    public String toString() {
        log.info(seleniumInstanceMap.toString());
        return "SeleniumInstanceConfig{" +
                "seleniumInstanceMap=" + seleniumInstanceMap +
                '}';
    }
}
