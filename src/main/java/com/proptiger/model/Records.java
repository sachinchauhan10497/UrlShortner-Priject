
/*
 * Records class
 * Author : Sachin Chauhan
 * 
 * This class is mapping to TABLE 'records' in the database
 * It is to store daily records of our system
 * 
 */

package com.proptiger.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proptiger.service.Constants;

@Entity
@Table(name="records",schema=Constants.schemaName)
public class Records {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="date",unique=true,nullable=false)
	private Date date;
	
	@Column(name="num_of_get",columnDefinition = "int default 0")
	private Integer numOfGet;
	
	@Column(name="num_of_post",columnDefinition="int default 0")
	private Integer numOfPost;
	
	@Column(name="num_of_expire_url",columnDefinition="int default 0")
	private Integer numOfExpireUrl;
	
	
	//////
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumOfGet() {
		return numOfGet;
	}

	public void setNumOfGet(Integer numOfGet) {
		this.numOfGet = numOfGet;
	}

	public Integer getNumOfPost() {
		return numOfPost;
	}

	public void setNumOfPost(Integer numOfPost) {
		this.numOfPost = numOfPost;
	}

	public Integer getNumOfExpireUrl() {
		return numOfExpireUrl;
	}

	public void setNumOfExpireUrl(Integer numOfExpireUrl) {
		this.numOfExpireUrl = numOfExpireUrl;
	}
	
}
