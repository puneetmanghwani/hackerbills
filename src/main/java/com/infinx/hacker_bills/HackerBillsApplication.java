package com.infinx.hacker_bills;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackerBillsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(HackerBillsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HackerBillsApplication.class, args);

		LOGGER.info("Hacker Bills Application Started");
	}

}
