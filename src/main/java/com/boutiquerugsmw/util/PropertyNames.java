package com.boutiquerugsmw.util;

import org.springframework.beans.factory.annotation.Value;

public class PropertyNames {

	public static final String SCHEDULED_TESTS_ENVIRONMENT = "${scheduled.tests.environment}";
	public static final String SCHEDULED_TESTS_PROJECT_PATH = "${scheduled.tests.project.path}";
//	public static final String SCHEDULED_TESTS_FILE_PATH = "${scheduled.tests.file.path}";
//	public static final String SCHEDULED_TESTS_FILE_PATH_FTP = "${scheduled.tests.file.path.ftp}";

//	public static final String SELENIUM_INSTANCES_IP_ADDRESSES = "${selenium.instances.ip.addresses}";
//	public static final String SELENIUM_INSTANCES_HOST_IDS = "${selenium.instances.host.ids}";
//	public static final String SELENIUM_INSTANCES_PORT = "${selenium.instances.port}";

	
//	public static final String IS_DEBUG_MODE_ACTIVE = "${executed.debug.mode}";

	public static final String BOUTIQUE_RUGS_USER_EMAIL = "${boutique.rugs.user.email}";
	public static final String BOUTIQUE_RUGS_PASSWORD = "${boutique.rugs.user.password}";
 	public static final String CHROME_DRIVER = "${chrome.driver}";
	public static final String TEST_RESULT_EMAIL_ADDRESS = "${test.result.email.address}";
	public static final String TEST_SCENARIO_CLASS_NAME = "${test.scenario.class.name}";

	public static final String FROM_EMAIL_ADDRESS = "${from.email.address}";
	public static final String FROM_EMAIL_USER_PASSWORD = "${from.email.user.password}";



}
