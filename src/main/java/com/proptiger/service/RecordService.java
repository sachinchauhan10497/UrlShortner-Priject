
/*
 * RecordService class
 * Author : Sachin Chauhan
 * 
 * This class contains core logic of save daily records
 * 
 */


package com.proptiger.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proptiger.model.Records;
import com.proptiger.repo.RecordsDao;

@Service
public class RecordService {

	@Autowired
	RecordsDao dao;	
	
	public void incrementNumOfGet() {
		dao.incrementNumOfGet();
	}
	
	public void incrementNumOfPost() {
		dao.incrementNumOfPost();
	}
	
	// Number of GET requests of given date 
	public Integer getNumberOfGet(Date date) {
			Records record = dao.findByDate(date);
			if(record!=null) {
				return record.getNumOfGet();
			}
			else return 0;
		}
	
	// Number of POST requests of given date
	public Integer getNumberOfPost(Date date) {
		Records record = dao.findByDate(date);
		if(record!=null) {
			return record.getNumOfPost();
		}
		else return 0;
	}
	
	// Report of given date
	public Records getRecords(String dateString) {
		try {
			Date date = stringToDate(dateString);
			return dao.findByDate(date);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	
	
	/////
	
	public Date getCurrentDate() {
		return  new Date(new java.util.Date().getTime());
	}
	
	
	@SuppressWarnings("deprecation")
	public Date stringToDate(String string) throws Exception{
		String split[] = string.split("-");
		return new Date(Integer.parseInt(split[2])-1900,Integer.parseInt(split[1])-1,
				     Integer.parseInt(split[0]));
	}
}
