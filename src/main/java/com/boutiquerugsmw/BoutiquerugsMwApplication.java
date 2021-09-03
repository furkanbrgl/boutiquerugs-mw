package com.boutiquerugsmw;

import com.boutiquerugsmw.model.ScheduledTestModel;
import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.repository.ScheduledTestsDao;
import com.boutiquerugsmw.service.SeleniumService;
import com.boutiquerugsmw.util.ApplicationConfigProp;
import com.boutiquerugsmw.util.BrNodeMaps;
import com.boutiquerugsmw.util.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableMongoAuditing
@EnableAsync
public class BoutiquerugsMwApplication{

	private static final Logger LOGGER = LogManager.getLogger(BoutiquerugsMwApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BoutiquerugsMwApplication.class, args);
	}

	@Autowired
	private BrNodeMaps brNodeMaps;

	@Autowired
	private ApplicationConfigProp applicationConfigProp;

	@Autowired
	private ScheduledTestsDao ScheduledTestsDao;

	@Autowired
	private SeleniumService seleniumService;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(applicationConfigProp.getScheduledTest().getFromEmailAddress().getUsername());
		mailSender.setPassword(applicationConfigProp.getScheduledTest().getFromEmailAddress().getPassword());

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
    public Map<String, SeleniumInstanceModel> SeleniumInstanceProfilesMap() throws IOException {
		//expected selenium nodes and actually ready-to-go nodes are compared.
		//to eliminate the junk selenium node ips come from app config
		List<String> instances = seleniumService.getSeleniumNodesByHubIp(applicationConfigProp.getSelenium().getHub().getIpAddress());

		for (String expectedNodeTag : applicationConfigProp.getSelenium().getInstances().getIpAddresses().keySet())
            {
            	for(String runningNodeIp: instances) {
            		if(runningNodeIp.contains(applicationConfigProp.getSelenium().getInstances().getIpAddresses().get(expectedNodeTag))) {

						//expected ones is really in the list of ones running on the server. (we will consider them to sent test)
						brNodeMaps.getSeleniumInstancesMap().put(expectedNodeTag, new SeleniumInstanceModel(
								applicationConfigProp.getSelenium().getInstances().getIpAddresses().get(expectedNodeTag), 000000L,
								applicationConfigProp.getSelenium().getInstances().getPort(),
								expectedNodeTag,
								"http://" + applicationConfigProp.getSelenium().getHub().getIpAddress() +
										":" + applicationConfigProp.getSelenium().getHub().getPort() + "/wd/hub",
								true,
								true
						));

					}
				}
            }
            return brNodeMaps.getSeleniumInstancesMap();
        }

	@PreDestroy
	public void onExit() {
		LOGGER.info("### BOUTIQUE RUGS MIDDLEWARE STOPPING ###");
		for (String key : applicationConfigProp.getSelenium().getInstances().getIpAddresses().keySet()) {
			SeleniumInstanceModel instance = brNodeMaps.getSeleniumInstancesMap().get(key);
			long runningTestID = instance.getRunningTestId();
			ScheduledTestModel testModel = ScheduledTestsDao.updateScheduledTestStatusByID(Constants.SCENARIO_STATUS_COMPLETED, runningTestID);

			LOGGER.info("### " + runningTestID + " has been pulled into UNCOMPLETED test.");
		}
	}

}
