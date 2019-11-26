package com.tamaki.twitter.tweet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamaki.twitter.domain.Pessoa;
import com.tamaki.twitter.repository.TweetStoreRepository;

@Service
public class TweetStoreService {
	
	@Autowired
    private TweetStoreRepository tweetRepository;
 
    public void salvar(long userId, String hashTag, String userName, String message, int follower) {
    	tweetRepository.save(new Pessoa(userId, hashTag, userName, message, follower));
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