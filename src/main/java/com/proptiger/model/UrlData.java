
/*
 * UrlData class
 * Author : Sachin Chauhan
 * 
 * This class is mapping to TABLE 'url_data' in the database
 * It is to store short to long URL mappings in database
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
@Table(name="url_data",schema=Constants.schemaName)
public class UrlData {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="short_url",length=6,unique=true,nullable=false)
	private String shortUrl;
	
	@Column(name="long_url",length=2083)
	private String longUrl;
	
	@Column(name="create_at")
	private Date createAt;
	
	@Column(name="expire_at")
	private Date expireAt;
	
	@Column(name="num_of_clicks",columnDefinition="int default 0")
	private Integer numOfClicks;
	
	
	///// 
	

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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public Integer getNumOfClicks() {
		return numOfClicks;
	}

	public void setNumOfClicks(Integer numOfClicks) {
		this.numOfClicks = numOfClicks;
	}
}
