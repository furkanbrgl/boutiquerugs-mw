package com.boutiquerugsmw;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.PropertyNames;
import com.boutiquerugsmw.util.BrNodeMaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableMongoAuditing
public class BoutiquerugsMwApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoutiquerugsMwApplication.class, args);
	}

	@Autowired
	private BrNodeMaps brNodeMaps;

	@Value("#{${selenium.instances.ip.addresses}}")
	private Map<String,String> seleniumInstancesIpAddresses;

	@Value(PropertyNames.SELENIUM_HUB_IP_ADDRESS)
	private String seleniumHubIpAddress;

	@Value(PropertyNames.SELENIUM_INSTANCES_PORT)
	private String seleniumInstancePort;

	@Value(PropertyNames.FROM_EMAIL_ADDRESS)
	private String fromEmailAddress;

	@Value(PropertyNames.FROM_EMAIL_USER_PASSWORD)
	private String fromEmailUserPassword;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(fromEmailAddress);
		mailSender.setPassword(fromEmailUserPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public Map<String, SeleniumInstanceModel> SeleniumInstanceProfilesMap() {

		for (String key : this.seleniumInstancesIpAddresses.keySet())
		{
			brNodeMaps.getSeleniumInstancesMap().put(key, new SeleniumInstanceModel(
					this.seleniumInstancesIpAddresses.get(key),
					seleniumInstancePort,
					key,
					seleniumHubIpAddress,
					true
			));
		}
		return brNodeMaps.getSeleniumInstancesMap();
	}

}
