package com.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.restapi.common.Global;
import com.restapi.common.RedisPool;


/**
 * 
 * @author Somendra.Raj5
 *
 */

@ComponentScan("com.rest.*")
@EnableAutoConfiguration()
@ImportResource({"classpath:spring-context.xml"})
@SpringBootApplication
public class RestWebservicesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RestWebservicesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RestWebservicesApplication.class, args);
		
		/*Load all properties*/
		//Global.loadClass();
		
		/*Load Redis*/
		//RedisPool.loadRedis();
		
		log.info("++++++++++++++++++++++++++++++++++++++++++");
		log.info("+++++++Rest Webservices Successfully++++++");
		log.info("++++++++++++++++++++++++++++++++++++++++++");
	}
}
