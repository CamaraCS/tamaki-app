package com.tamaki.twitter.tweet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Renato.T.Tamaki
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(){
		
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
}
