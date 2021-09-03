package com.boutiquerugsmw.controller;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.service.SeleniumService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selenium")
public class SeleniumController {

    @Autowired
    private SeleniumService seleniumService;

    private static final Logger LOGGER = LogManager.getLogger(SeleniumController.class);

    @RequestMapping(value = "/app/nodes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SeleniumInstanceModel> getAppSeleniumNodes( ) throws Exception {
        LOGGER.info("Entering in rest endpoint to get Selenium Nodes");
        return seleniumService.getAppSeleniumNodes();
    }
    @PostMapping(value = "/getSessionsByNodeIp")
    @ResponseStatus(HttpStatus.OK)
    public String getSessionsByNodeIp(@RequestBody String nodeIp){
        LOGGER.info("Entering in rest endpoint to get Selenium Node (" + nodeIp + ") Sessions");
        return seleniumService.getSessionsByNodeIp(nodeIp);
    }

    @DeleteMapping("/deleteSessionByNodeIp/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSessionByNodeIp(@PathVariable String sessionId, @RequestBody String nodeIp) {
        LOGGER.info("Entering in rest endpoint to get Selenium (" + nodeIp + ") Delete Sessions for " + sessionId);
        seleniumService.deleteSessionByNodeIp(sessionId, nodeIp);
    }

    @PostMapping(value = "/getNodesByHubIp")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getSeleniumNodesByHubIp(@RequestBody String hubIp) throws Exception {
        LOGGER.info("Entering in rest endpoint to get Selenium Node (" + hubIp + ") Instances");
        return seleniumService.getSeleniumNodesByHubIp(hubIp);
    }

    @PostMapping(value = "/nodeWellBeingByNodeIp")
    @ResponseStatus(HttpStatus.OK)
    public String checkSeleniumNodeWellBeingByNodeId(@RequestBody String nodeIp) {
        LOGGER.info("Entering in rest endpoint to get Selenium (" + nodeIp + ") Well-being");
        return seleniumService.checkSeleniumNodeWellBeingByNodeId(nodeIp);
    }
    @PostMapping(value = "/hubWellBeingByHubIp")
    @ResponseStatus(HttpStatus.OK)
    public String checkSeleniumHubWellBeingByHubIp(@RequestBody String hubIp) {
        LOGGER.info("Entering in rest endpoint to get Selenium Hub (" + hubIp + ") Well-being");
        return seleniumService.checkSeleniumHubWellBeingByHubIp(hubIp);
    }

}
