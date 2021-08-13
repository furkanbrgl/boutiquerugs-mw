package com.boutiquerugsmw;

import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/*
@SpringBootTest
class BoutiquerugsMwApplicationTests {


	@Autowired
    ScheduledTestsStarter scheduledTestsStarter;

	@Autowired
	ScheduledTestsDaoImpl scheduledTestsRepository;

	private final String testName = "LoginTest";


	@Value(PropertyNames.BOUTIQUE_RUGS_USER_EMAIL)
	private String boutiqueRugsUserEmail;

	@Value(PropertyNames.BOUTIQUE_RUGS_PASSWORD)
	private String boutiqueRugsUserPassword;

	@Value(PropertyNames.CHROME_DRIVER)
	private String chromeDriver;

	@Value(PropertyNames.TEST_RESULT_EMAIL_ADDRESS)
	private String testResultEmailAddress;

	@Test
	void contextLoads() throws MessagingException {
		scheduledTestsStarter.startTest(scheduledTestsRepository.getScheduledTests(System.currentTimeMillis(),
				testName,getAvailableSeleniumInstance(),
				this.getScheduledTestParams(System.currentTimeMillis())));
	}

	private SeleniumInstanceModel getAvailableSeleniumInstance() {

		return new SeleniumInstanceModel();
	}


	private Map<String, String> getScheduledTestParams(long testID ) {

		Map <String, String> result = new HashMap<String, String>();
		result.put("boutiqueRugsUserEmail", boutiqueRugsUserEmail);
		result.put("boutiqueRugsUserPassword", boutiqueRugsUserPassword);
		result.put("chromeDriver", chromeDriver);
		result.put("testResultEmailAddress", testResultEmailAddress);

		return result;
	}

}
*/