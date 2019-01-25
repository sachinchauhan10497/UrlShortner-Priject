
/*
 * RecordsDao interface
 * Author : Sachin Chauhan
 * 
 * This interface is to perform operations on the database TABLE 'records'
 * It is to store daily records of our system
 * 
 */

package com.proptiger.repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.proptiger.model.Records;

public interface RecordsDao extends JpaRepository<Records, Integer>{

	public Records findByDate(Date date);
	
	
	@Modifying
	@Transactional
	@Async
	@Query(value="UPDATE url_shortener.records SET num_of_get = num_of_get+1 "
			+ "WHERE date=date(now())",nativeQuery=true)
	public void incrementNumOfGet();
	
	@Modifying
	@Transactional
	@Async
	@Query(value="UPDATE url_shortener.records SET num_of_post = num_of_post+1 "
			+ "WHERE date=date(now())",nativeQuery=true)
	public void incrementNumOfPost();

	@Modifying
	@Transactional
	@Async
	@Query(value="INSERT INTO url_shortener.records(date) VALUES(date(now()))",nativeQuery=true)
	public void addTodayDate();
		
}
