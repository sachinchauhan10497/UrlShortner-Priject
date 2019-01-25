

/*
 * HistoricalData class
 * Author : Sachin Chauhan
 * 
 * This class is mapping to TABLE 'historical_data' in the database
 * It is storing all the historical data included expired and deleted data
 * 
 */

package com.proptiger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proptiger.service.Constants;

@Entity
@Table(name="historical_data",schema=Constants.schemaName)
public class HistoricalData {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="short_url",length=6)
	private String shortUrl;
	
	@Column(name="long_url",length=2083)
	private String longUrl;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="expired_at")
	private Date expiredAt;
	
	@Column(name="num_of_clicks",columnDefinition="int default 0")
	private Integer numOfClicks;

	
	
	////
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Integer getNumOfClicks() {
		return numOfClicks;
	}

	public void setNumOfClicks(Integer numOfClicks) {
		this.numOfClicks = numOfClicks;
	}
	
}
