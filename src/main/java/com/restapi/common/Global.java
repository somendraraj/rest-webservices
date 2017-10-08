package com.restapi.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @author Somendra.Raj5
 *
 */
public class Global {
	
	private static final Logger log = LoggerFactory.getLogger(Global.class);
	
	private static boolean externalConfigFile = true;
	
	private static Properties propConfig;
	
	/*Redis Config*/
	public static boolean IS_REDIS_CACHE = true;
	public static String REDIS_ENV;
	
	/*Application Context*/
	public static ApplicationContext ctx = null;
	
	/*load all properties files*/
	public static void loadClass() {
		log.info("***************Loading All Properties***************");
	}
	
	static{
		try{
			if(externalConfigFile){
				log.info("*****External Config File**********");
				propConfig = new Properties();
				propConfig.load(new FileInputStream("./config.properties"));
			}else{
				log.info("*****External Config File**********");
				propConfig = PropertiesLoaderUtils.loadAllProperties("config.properties");
			}
			
			REDIS_ENV = propConfig.getProperty("redis.env");
			
			
		}catch(FileNotFoundException ex){
			log.error("FileNotFoundException in loading properties file", ex);
		}catch(Exception e){
			log.error("Exception in loading properties file", e);
		}
	}

}
