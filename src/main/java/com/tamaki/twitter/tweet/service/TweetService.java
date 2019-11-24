package com.tamaki.twitter.tweet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Renato.T.Tamaki
 */

@Component
public class TweetService {
	

	@Value("${twitter.consumer.key}")
	private String twitterConsumerKey;
	
	@Value("${twitter.consumer.key.secret}")
	private String twitterConsumerKeySecret;
	
	@Value("${twitter.access.token}")
	private String twitterAccessToken;
	
	@Value("${twitter.access.token.secret}")
	private String twitterAccessTokenSecret;
	
	private Twitter getTwitterInstance(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey(twitterConsumerKey)
			.setOAuthConsumerSecret(twitterConsumerKeySecret)
			.setOAuthAccessToken(twitterAccessToken)
			.setOAuthAccessTokenSecret(twitterAccessTokenSecret);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		return(twitter);
	}
	
	public List<String> getHashTag(String hashtag){
		List<String> tweetsrHashTags = new ArrayList<String>();
		try{
			Twitter twitter = getTwitterInstance();
			int firstIndex = hashtag.indexOf('#');
			Query query;
			if(firstIndex == 0) {
				query = new Query(hashtag);
			}else {
				query = new Query('#' + hashtag);
			}
			query.setCount(100);
		    QueryResult result = twitter.search(query);
		    List<Status> tweets = result.getTweets(); 
		   
		    for (Status tweet : tweets) {
		    	tweetsrHashTags.add(" USUARIOS ID " + tweet.getUser().getId());
		    	tweetsrHashTags.add(" USUARIOS NAME "+ tweet.getUser().getName());
		    	tweetsrHashTags.add(" MENSAGEM " + tweet.getText() + "#######" + '\n');
		    	tweetsrHashTags.add(" SEGUIDORES " + tweet.getUser().getFollowersCount() + "#######" + '\n');
		    } 	
		}catch(TwitterException te){
			tweetsrHashTags.add(te.getMessage());
		}
		return(tweetsrHashTags);
	}
}
