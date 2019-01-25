
/*
 * CachSupport class
 * Author : Sachin Chauhan
 * 
 * This class contains all the cacheable methods connected to Redis Server..
 * 
 */

package com.proptiger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;

import com.proptiger.repo.UrlDao;

@Controller
public class CachSupport {
	
	@Autowired
	private UrlDao dao;
	
		@Cacheable("findByShortUrlCached")
		public String findByShortUrlCached(String shortUrl) {
			
		//  Remove these comments to test REDIS CACHING... 	
		//	System.out.println("Inside findByShortUrlCached");
		//	try { Thread.sleep(1000);
		//	} catch (Exception e) {}
			
			
			String ans = dao.findByShortUrl(shortUrl).getLongUrl();
			return ans;
		}

}
