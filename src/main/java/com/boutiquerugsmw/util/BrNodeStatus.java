package com.boutiquerugsmw.util;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

/**
 * TODO: Checking on the node that is able to connect to the HUB.
 *
 * IT WILL CHANGE !!
 */
@Component
public class BrNodeStatus {

    private static final Logger logger = Logger.getLogger(BrNodeStatus.class);

    public boolean isNodeReachable(String NodeIpAddress) throws IOException {

        {
            InetAddress geek = InetAddress.getByName(NodeIpAddress);
            System.out.println("Sending Ping Request to " + NodeIpAddress);
            if (geek.isReachable(5000)){
                logger.info(NodeIpAddress + " IS REACHABLE :::");
                return true;
            }
            else {
                logger.warn(NodeIpAddress + " IS NOT REACHABLE :::");
                return false;
            }
        }
    }

}
