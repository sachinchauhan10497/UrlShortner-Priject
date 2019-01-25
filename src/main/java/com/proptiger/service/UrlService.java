
/*
 * UrlService class
 * Author : Sachin Chauhan
 * 
 * This class contains core logic of short to long URL mappings
 * Note that we are generating short URLs randomly 
 * 
 */

package com.proptiger.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proptiger.model.UrlData;
import com.proptiger.repo.UrlDao;


@Service
public class UrlService {
	
	@Autowired
	private UrlDao dao;
	
	@Autowired
	private CachSupport cachSupport;
	
	
	public UrlData getUrlData(Integer id) {
		return dao.findOne(id);
	}
	
	public String shortUrl(String longUrl) {
		
		/// Core Logic		
		String outPutShortUrl = randomShortUrl();

		if(dao.findByShortUrl(outPutShortUrl)==null)
			{ // If Generated URL is not present already, mapped it and save it. 
			
			UrlData urlData = new UrlData();
			urlData.setLongUrl(longUrl);
			urlData.setShortUrl(outPutShortUrl);
			urlData.setCreateAt(new Date());
			urlData.setExpireAt(new Date(new Date().getTime()+Constants.urlExpireTime));
			urlData.setNumOfClicks(0);
			dao.save(urlData);
			
			outPutShortUrl =  Constants.OUR_DOMAIN_NAME+outPutShortUrl;
			return outPutShortUrl;
			}
		
		else { // If Generated URL is already present, generate it again.
			return shortUrl(longUrl);
		}
		
	}
	
	public String longUrl(String shortUrl) {
		/// Core Logic
		try {
		String longUrl = cachSupport.findByShortUrlCached(shortUrl);
			dao.incrementNumOfClicks(shortUrl);
			return longUrl;
		}
		catch (Exception e) {
			return Constants.INVALID_GET_MESSAGE;
		}
	}
	
	// Number of clicks on given short URL
	public Integer getNumOfClicksOnUrl(String shortUrl) {
		try {
		return dao.findByShortUrl(shortUrl).getNumOfClicks();
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	// All the data related to given short URL
	public UrlData getDataOfUrl(String shortUrl) {
		return dao.findByShortUrl(shortUrl);
	}
	
	
	public String randomShortUrl()
	// It Generates Random String in Base62 of length = length(parameter) 
	{
		return Base62.randomString(Constants.SHORT_URL_LENGTH);
	}
}
