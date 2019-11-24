package com.tamaki.twitter.tweet.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tamaki.twitter.tweet.exception.ResourceNotFoundException;
import com.tamaki.twitter.tweet.service.TweetService;

/**
 *
 * @author Renato.T.Tamaki
 */

@RestController
@RequestMapping("/tweets")
public class TweetController {
	
	@Inject
	private TweetService twitterService;
	
	@RequestMapping(value="/hashtags/{hashTag}", method=RequestMethod.GET)
	public ResponseEntity<?> getHashTag(@PathVariable String hashTag){
		if((null == hashTag) || "".equals(hashTag)){
			throw new ResourceNotFoundException("HashTag não Fornecida"); 
		}
		Iterable<String> hashTags = twitterService.getHashTag(hashTag);
		if(twitterService.getHashTag(hashTag).isEmpty()){
			return(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		return(new ResponseEntity<>(hashTags, HttpStatus.OK));
	}
}
