package com.boutiquerugsmw.service;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.ApplicationConfigProp;
import com.boutiquerugsmw.util.BrNodeMaps;
import com.boutiquerugsmw.util.BrNodeStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SeleniumService {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumService.class);

    @Autowired
    private ApplicationConfigProp configProp;

    @Autowired
    private BrNodeStatus brNodeStatus;

    @Autowired
    private BrNodeMaps brNodeMaps;

    public List<SeleniumInstanceModel> getAppSeleniumNodes() throws IOException {

        List<SeleniumInstanceModel> list = new ArrayList<>();

        for (Map.Entry<String,SeleniumInstanceModel> SIntanceMap : brNodeMaps.getSeleniumInstancesMap().entrySet())
        {
            boolean b = brNodeStatus.isNodeReachable(SIntanceMap.getValue().getIpAddress());
            SIntanceMap.getValue().setReachable(b);

            if(b==true) {
                if(SIntanceMap.getValue().getRunningTestId() != 000000L) {
                    SIntanceMap.getValue().setAvailable(false);
                } else {
                    SIntanceMap.getValue().setAvailable(true);
                }
            } else {
                SIntanceMap.getValue().setAvailable(false);
            }

            list.add(SIntanceMap.getValue());

        }
        return list;
    }

    public void deleteSessionByNodeIp(String sessionId, String nodeIp) {

        String uri = "http://" +nodeIp+ ":" + configProp.getSelenium().getInstances().getPort() +"/wd/hub/session/" + sessionId;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }

    public String getSessionsByNodeIp(String nodeIp) {

        String uri = "http://" +nodeIp+ ":" + configProp.getSelenium().getInstances().getPort() +"/wd/hub/sessions";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        LOGGER.info(result.toString());
        return result;
    }

    public String checkSeleniumNodeWellBeingByNodeId(String nodeIp) {

        String uri = "http://" +nodeIp+ ":" + configProp.getSelenium().getInstances().getPort() +"/wd/hub/status";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        LOGGER.info(result.toString());
        return result;
    }

    public String checkSeleniumHubWellBeingByHubIp(String hubIp) {

        String url = "http://" + hubIp + ":" + configProp.getSelenium().getHub().getPort() +"/status";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.info(result.toString());
        return result;
    }

    public List<String> getSeleniumNodesByHubIp(String hubIp) throws IOException {

        String url = "http://" + hubIp + ":" + configProp.getSelenium().getHub().getPort() +"/grid/console";

        List<String> instances = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

       Elements elements = doc.getElementsByClass("proxyid");

        elements.forEach(e -> {
            instances.add(e.toString().substring(31, e.toString().length() - 21));
        });

        return instances;
    }


}
