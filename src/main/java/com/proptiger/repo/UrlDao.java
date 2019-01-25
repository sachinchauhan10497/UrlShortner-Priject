
/*
 * UrlDao interface
 * Author : Sachin Chauhan
 * 
 * This interface is to perform operations on the database TABLE 'url_data'
 * It is to store short to long URL mappings in database
 * 
 */


package com.proptiger.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.proptiger.model.UrlData;

public interface UrlDao extends JpaRepository<UrlData, Integer>{

	public UrlData findByShortUrl(String shortUrl);
	
	
	@Modifying
	@Transactional
	@Async
	@Query(value="UPDATE url_shortener.url_data SET num_of_clicks = num_of_clicks+1 "
			+ "WHERE short_url=?1",nativeQuery=true)
	public void incrementNumOfClicks(String shortUrl);
	
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM url_shortener.url_data WHERE now()>expire_at",nativeQuery=true)
	public void deleteExpireData();
	
}
