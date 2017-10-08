package com.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestWebservicesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RestWebservicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestWebservicesApplication.class, args);
		
		
		log.info("++++++++++++++++++++++++++++++++++++++++++");
		log.info("+++++++Service Started Successfully+++++++");
		log.info("++++++++++++++++++++++++++++++++++++++++++");
	}
}
