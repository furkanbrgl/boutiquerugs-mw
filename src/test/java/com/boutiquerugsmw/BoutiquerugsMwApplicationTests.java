package com.boutiquerugsmw;

import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import com.boutiquerugsmw.model.SeleniumInstanceModel;
import com.boutiquerugsmw.service.ScheduledTestsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class BoutiquerugsMwApplicationTests {

	@Autowired
	ScheduledTestsService scheduledTestsService;

	@Autowired
	ScheduledTestsDaoImpl scheduledTestsRepositoryImpl;

	@Test
	void contextLoads() throws MessagingException {
		scheduledTestsService.startTest(scheduledTestsRepositoryImpl.getScheduledTests(System.currentTimeMillis()), new SeleniumInstanceModel());
	}

}
