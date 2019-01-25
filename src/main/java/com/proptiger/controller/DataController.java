
/*
 * DataController Class
 * Author : Sachin Chauhan
 * 
 *  This class maps all API Calls to Java Methods 
 *  and calls Service class's methods to execute core logic
 */

package com.proptiger.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proptiger.model.Records;
import com.proptiger.model.UrlData;
import com.proptiger.service.Constants;
import com.proptiger.service.RecordService;
import com.proptiger.service.UrlService;



@Controller
public class DataController {
	
	@Autowired
	private UrlService usrlService;
	
	@Autowired
	private RecordService recordService;
	
	
	// Greeting - Just for testing API calling
	@RequestMapping(value="/ping")
	@ResponseBody
	public String ping() {
		return Constants.pingMessage;
	}
	
	
	// POST API to receive short URL and return Long URL
	@RequestMapping(value="/post",method=RequestMethod.POST)
	@ResponseBody
	public String getShortUrl(@RequestBody String longUrl) {
		recordService.incrementNumOfPost();
		return usrlService.shortUrl(longUrl);
   }
	
	
	// GET API to receive long URL and return Short URL
	@RequestMapping(value="{url}",method=RequestMethod.GET)
	@ResponseBody
	public String getLongUrl(@PathVariable String url,HttpServletResponse httpServletResponse){
		
		// Increment number of clicks
		recordService.incrementNumOfGet();
		
		// Auto Redirection to original long URL
		if(Constants.IS_REDIRECT) {
			try {
				httpServletResponse.sendRedirect(usrlService.longUrl(url));
				}
			catch (Exception e) {
				return Constants.REDIRECT_FAIL_MESSAGE;
				}
			
			 return "Redirected Successfully";
			}
		
		else {
			return usrlService.longUrl(url);
		}		

   }
	
	
	
	/// API to see records of given date
	/// TYPE DATE IN DD-MM-YYYY FORMAT IN GET REQUEST
	@RequestMapping(value="records/{date}",method=RequestMethod.GET)
	@ResponseBody
	public Records getRecords(@PathVariable String date){
		return recordService.getRecords(date);
   }
	
	
	/// API to see Number of Clicks on given short URL
	@RequestMapping(value="clicks/{url}",method=RequestMethod.GET)
	@ResponseBody
	public Integer getNumOfClicksOnUrl(@PathVariable String url){
		return usrlService.getNumOfClicksOnUrl(url);
   }
	
	/// API to see all the data related to given short URL
	@RequestMapping(value="data/{url}",method=RequestMethod.GET)
	@ResponseBody
	public UrlData getDataOfUrl(@PathVariable String url){
		return usrlService.getDataOfUrl(url);
   }
	
}
