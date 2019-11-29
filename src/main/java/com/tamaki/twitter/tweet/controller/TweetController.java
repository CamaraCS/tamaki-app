package com.tamaki.twitter.tweet.controller;

import javax.inject.Inject;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tamaki.twitter.domain.Pessoa;
import com.tamaki.twitter.tweet.exception.ResourceNotFoundException;
import com.tamaki.twitter.tweet.service.TweetService;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/tweets")
public class TweetController implements ErrorController {
	
	@Inject
	private TweetService twitterService;
	
	private static final String PATH="/error";
	
	@RequestMapping(value="/hashtags/{hashTag}", method=RequestMethod.GET)
	public ResponseEntity<?> getHashTag(@PathVariable String hashTag) throws TwitterException, JsonProcessingException{
		if((null == hashTag) || "".equals(hashTag)){
			throw new ResourceNotFoundException("HashTag não Fornecida"); 
		}
		Iterable<Pessoa> hashTags = twitterService.getHashTag(hashTag);
		if(twitterService.getHashTag(hashTag).isEmpty()){
			return(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		return(new ResponseEntity<>(hashTags, HttpStatus.OK));
	}
	
	@RequestMapping(value="/list/{hashTag}", method=RequestMethod.GET)
	public ResponseEntity<?> twitterService(@PathVariable String hashTag) throws TwitterException{
		if((null == hashTag) || "".equals(hashTag)){
			throw new ResourceNotFoundException("HashTag não cadastrada no Banco de Dados"); 
		}
		Iterable<Pessoa> hashTags = twitterService.listDBHashTag(hashTag);
		if(twitterService.listDBHashTag(hashTag).isEmpty()){
			return(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		return(new ResponseEntity<>(hashTags, HttpStatus.OK));
	}
	
	@RequestMapping(value="/toplist/{hashTag}", method=RequestMethod.GET)
	public ResponseEntity<?> twitterTopService(@PathVariable String hashTag) throws TwitterException{
		if((null == hashTag) || "".equals(hashTag)){
			throw new ResourceNotFoundException("HashTag não cadastrada no Banco de Dados"); 
		}
		Iterable<Pessoa> hashTags = twitterService.listDBTopHashTag(hashTag);
		if(twitterService.listDBTopHashTag(hashTag).isEmpty()){
			return(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
		return(new ResponseEntity<>(hashTags, HttpStatus.OK));
	}
	
	@RequestMapping(value=PATH, method=RequestMethod.GET)
	public String defaultErrorMessage() {
		return "Hash Tag não definida";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}