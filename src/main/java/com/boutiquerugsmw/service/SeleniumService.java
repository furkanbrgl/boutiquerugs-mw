package com.boutiquerugsmw.service;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.BrNodeMaps;
import com.boutiquerugsmw.util.BrNodeStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SeleniumService {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumService.class);

    @Autowired
    private BrNodeStatus brNodeStatus;

    @Autowired
    private BrNodeMaps brNodeMaps;

    public List<SeleniumInstanceModel> getSeleniumNodes() throws IOException {

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
}
