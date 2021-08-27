package com.boutiquerugsmw.controller;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.service.SeleniumService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/selenium")
public class SeleniumController {

    @Autowired
    private SeleniumService seleniumService;

    private static final Logger LOGGER = LogManager.getLogger(SeleniumController.class);

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SeleniumInstanceModel> getNodes( ) throws IOException {
        LOGGER.info("Entering in rest endpoint to get Selenium Nodes");
        return seleniumService.getSeleniumNodes();
    }

}
