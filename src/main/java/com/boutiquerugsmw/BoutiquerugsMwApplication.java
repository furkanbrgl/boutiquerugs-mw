package com.boutiquerugsmw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BoutiquerugsMwApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoutiquerugsMwApplication.class, args);
	}

}
