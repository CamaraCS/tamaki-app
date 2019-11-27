package com.tamaki.twitter.tweet.service;

import static org.springframework.data.mongodb.core.query.Query.query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tamaki.twitter.domain.Pessoa;
import com.tamaki.twitter.repository.ITweetStoreRepository;

public class TweetStoreService {
	
	@Autowired
    private ITweetStoreRepository tweetRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	
    public void salvar(BigInteger id, long userId, String hashTag, String userName, String message, int follower) {
    	tweetRepository.save(new Pessoa(id, userId, hashTag, userName, message, follower));
    }
 
    public List<Pessoa> findAll() {
       return tweetRepository.findAll();
    }
 
    public long count() {
        return tweetRepository.count();
    }
 
    public void delete(String userId) {
    	tweetRepository.deleteAll();
    }
}