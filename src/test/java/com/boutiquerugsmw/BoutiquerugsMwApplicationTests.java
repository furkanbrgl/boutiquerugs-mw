package com.boutiquerugsmw;

import com.boutiquerugsmw.repository.impl.ScheduledTestsDaoImpl;
import com.boutiquerugsmw.service.ScheduledTestsStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class BoutiquerugsMwApplicationTests {


	@Autowired
    ScheduledTestsStarter scheduledTestsStarter;

	@Autowired
	ScheduledTestsDaoImpl scheduledTestsRepository;

	@Test
	void contextLoads() throws MessagingException {
		scheduledTestsStarter.startTest(scheduledTestsRepository.getScheduledTests(System.currentTimeMillis()));
	}

}
