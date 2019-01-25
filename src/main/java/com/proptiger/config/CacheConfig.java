
/*
 * CacheConfig class
 * Author : Sachin Chauhan
 * 
 *  This class is for Connect Redis server for 
 */

package com.proptiger.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.proptiger.service.Constants;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport{
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(Constants.redisHostName);
		redisConnectionFactory.setPort(Constants.redisPortNumber);
		// System.out.println("Redis Connection Factory created");
		return redisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		// System.out.println("Redis Template created");
		return redisTemplate;
	}
	
	@Bean
	  public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
	   RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(300);
	    // System.out.println("Cache manager created");
	    return cacheManager;
	  }
}
