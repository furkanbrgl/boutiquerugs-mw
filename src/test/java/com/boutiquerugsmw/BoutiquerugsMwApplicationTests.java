package com.boutiquerugsmw;

import com.boutiquerugsmw.core.ScheduledTests;
import com.boutiquerugsmw.doa.ScheduledTestsDao;
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
	ScheduledTestsDao scheduledTestsDao;

	@Test
	void contextLoads() throws MessagingException {
		scheduledTestsService.startTest(scheduledTestsDao.getScheduledTests(), new SeleniumInstanceModel());
	}

}
