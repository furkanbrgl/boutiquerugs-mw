package com.boutiquerugsmw;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.util.ApplicationConfigProp;
import com.boutiquerugsmw.util.BrNodeMaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableMongoAuditing
@EnableAsync
public class BoutiquerugsMwApplication{

	public static void main(String[] args) {
		SpringApplication.run(BoutiquerugsMwApplication.class, args);
	}

	@Autowired
	private BrNodeMaps brNodeMaps;

	@Autowired
	private ApplicationConfigProp applicationConfigProp;

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
        public Map<String, SeleniumInstanceModel> SeleniumInstanceProfilesMap() {

            for (String key : applicationConfigProp.getSelenium().getInstances().getIpAddresses().keySet())
            {
                brNodeMaps.getSeleniumInstancesMap().put(key, new SeleniumInstanceModel(
						applicationConfigProp.getSelenium().getInstances().getIpAddresses().get(key),
						applicationConfigProp.getSelenium().getInstances().getPort(),
                        key,
						applicationConfigProp.getSelenium().getHub().getIpAddress(),
                        true
                ));
            }
            return brNodeMaps.getSeleniumInstancesMap();
        }

}
