
/*
 * Constants Interface
 * Author : Sachin Chauhan
 * 
 *  This interface contains all the basic constants used in core logic of the application,
 */

package com.proptiger.service;

public interface Constants {
	
	final String OUR_DOMAIN_NAME 			= "localhost:8080/UrlShortner/";
	final Integer SHORT_URL_LENGTH 			= 6;
	
	// The character set for generate short URL 
	final String ALPHABET 					= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	final int BASE 							= ALPHABET.length();
	
	// Parameters to connect with database
	final String driverClassName			= "com.mysql.jdbc.Driver";
	final String DbHostName 				= "jdbc:mysql://127.0.0.1:3306";
	final String DbUserName					= "root";
	final String DbPassword					= "";
	final String schemaName					= "url_shortener";
	
	
	// Redis Properties
	final String redisHostName				= "127.0.0.1";
	final int redisPortNumber				= 6379;
	
	//Redirection in GET request...
	// If set true then GET request will be auto redirect to original long URL
	final Boolean IS_REDIRECT 				= false;
	final String REDIRECT_FAIL_MESSAGE		= "CAN'T REDIRECT TO YOUR ORIGINAL URL";
	
	final String INVALID_GET_MESSAGE		= "YOU HAVE INSERTED INVALID SHORT URL OR YOUR URL HAS BEEN EXPIRED";
	
	//Add Today's date in database
	// at 12:00 AM every day
    final String AddDateCronTime			= "0 0 0 * * ?";
    
    // Remove Expire Data after few Minutes 
    final long expireDataCronTimeInMinutes	= 60; // In Minutes
    final long expireDataCronTime			= expireDataCronTimeInMinutes*1000l*60l; // Converted Into Milliseconds
    
    // Expiration time of one URL
    final long urlExpireTimeInMinutes		= 60*24; // In Minutes;
    final long urlExpireTime				= urlExpireTimeInMinutes*1000L*60L; // Converted Into Milliseconds
    
    // Greetings Message
    final String pingMessage				= "Welcome to the Lil URL Service";
}
