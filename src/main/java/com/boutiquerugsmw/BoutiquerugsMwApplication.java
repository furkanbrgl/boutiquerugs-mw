package com.boutiquerugsmw;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.PropertyNames;
import com.boutiquerugsmw.util.SeleniumInstanceConfig;
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
	private SeleniumInstanceConfig seleniumInstanceConfig;

	@Value("#{${selenium.instances.ip.addresses}}")
	private Map<String,String> seleniumInstancesIpAddresses;

	@Value(PropertyNames.SELENIUM_HUB_IP_ADDRESS)
	private String seleniumHubIpAddress;

	@Value(PropertyNames.SELENIUM_INSTANCES_PORT)
	private String seleniumInstancePort;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("boutiquerugstest@gmail.com");
		mailSender.setPassword("boutiquerugs123!");

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
			seleniumInstanceConfig.getSeleniumInstanceMap().put(key, new SeleniumInstanceModel(
					this.seleniumInstancesIpAddresses.get(key),
					seleniumInstancePort,
					key,
					seleniumHubIpAddress,
					true
			));
		}
		return seleniumInstanceConfig.getSeleniumInstanceMap();
	}

}
