package com.restapi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 
 * @author Somendra.Raj5
 *
 */
public class RedisPool {

	static Logger log = LoggerFactory.getLogger(RedisPool.class);

	private static ShardedJedisPool jedisPool = null;

	private static final String applicationContextFile = "classpath:spring-context.xml";

	/* For loading the Redis server and make it initialize */
	public static void loadRedis() {
		log.info("*********Redis instance creation***********");
		ApplicationContext applicationContext = null;
		String[] fileUrl = new String[] { applicationContextFile };
		applicationContext = new ClassPathXmlApplicationContext(fileUrl);
		Global.ctx = applicationContext;

		if (Global.REDIS_ENV.equalsIgnoreCase("redisEnv1")) {
			log.info("********Redis Env******");
			jedisPool = (ShardedJedisPool) getBean("jedis");
			log.info("********Redis pool******" + jedisPool);
		}

		log.info("*******Application Context*******\n" + Global.ctx.toString());
	}

	/**
	 * method get bean
	 * 
	 * @param name
	 * @return
	 */
	private static Object getBean(String name) {
		Object o = null;
		o = Global.ctx.getBean(name);
		return o;
	}

	/**
	 * creating redis pool
	 * 
	 * @return
	 */
	private static ShardedJedis getRedisPool() {
		ShardedJedis redisCli = null;
		try {
			log.info("********Redis Call Start*********");
			log.info("Jedis Pool: " + jedisPool);
			redisCli = jedisPool.getResource();
		} catch (JedisConnectionException jc) {
			log.error("JedisConnection Error ", jc);
		}
		return redisCli;
	}

	/**
	 * return redis resource
	 * 
	 * @param redisClient
	 */
	private static void returnResource(ShardedJedis redisClient) {
		try {
			jedisPool.returnResource(redisClient);
		} catch (JedisException je) {
			log.error("Error while returning RedisResource", je);
		}
	}

	public static Object getObjByKey(String key) {
		ShardedJedis redisClient = null;
		Object returnObj = null;
		try {
			redisClient = getRedisPool();
			if (redisClient != null) {
				returnObj = redisClient.getOb(key);
				log.info("*******Getting Object from Redis for key: " + key + "-" + returnObj);
			} else {
				log.warn("***************RedisClient is NULL****************");
			}
		} catch (JedisConnectionException jce) {
			log.error("*****JedisConnectionException while getting object from Redis: ", jce);
		} catch (Exception e) {
			log.error("***********Exception while getting object from Redis:*********** ", e);
		} finally {
			returnResource(redisClient);
		}
		return returnObj;
	}

	public static void setObjForKey(String key, Object value, Integer seconds) {
		ShardedJedis redisClient = null;
		try {
			redisClient = getRedisPool();
			if (redisClient != null) {
				if (seconds != null) {
					redisClient.set(key, seconds, value);
				} else {
					redisClient.set(key, value);
				}
				log.info("********Setting Object to Redis: *********" + value + " FOR: " + key);
			} else {
				log.warn("**************RedisClient is NULL*************");
			}
		} catch (JedisConnectionException jce) {
			log.error("******JedisConnectionException while setting object to Redis: *******", jce);
		} catch (Exception e) {
			log.error("********Exception while setting object to Redis:********* ", e);
		} finally {
			returnResource(redisClient);
		}
	}

}
