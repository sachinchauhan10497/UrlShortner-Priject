
/*
 * Scheduler class
 * Author : Sachin Chauhan
 * 
 * This class contains all the Scheduled methods that runs periodically
 */

package com.proptiger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.proptiger.repo.RecordsDao;
import com.proptiger.repo.UrlDao;
import com.proptiger.service.Constants;

@Controller
public class Scheduler {
	
	@Autowired
	RecordsDao recordsDao;
	
	@Autowired
	UrlDao urlDao;

	// Add Today's date is database
	// at 12:00 AM every day
    @Scheduled(cron=Constants.AddDateCronTime)
	public void addToday() {
		recordsDao.addTodayDate();
		// System.out.println("Today's Date Added To the Databse");
	}
    
    
    // Cron to remove all the expired data and store it as a historical data
    @Scheduled(fixedRate=Constants.expireDataCronTime)
    public void removeExpireData() {
    	urlDao.deleteExpireData();
    	// System.out.println("Expired Data Deleted.. Database is Now Clean...");
    }
}
