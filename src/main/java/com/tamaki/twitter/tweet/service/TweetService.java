package com.tamaki.twitter.tweet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.tamaki.twitter.domain.Pessoa;
import com.tamaki.twitter.repository.TweetStoreRepository;

import twitter4j.Query;
import twitter4j.Query.ResultType;
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
@EnableMongoRepositories(basePackageClasses = TweetStoreRepository.class)
public class TweetService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TweetService.class);
	@Autowired 
	private TweetStoreRepository tweetsRepository;
	
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
	
	public List<Pessoa> getHashTag(String hashtag){
		tweetsRepository.deleteAll();
		List<Pessoa> tweetsrHashTags = new ArrayList<Pessoa>();
		try{
			Twitter twitter = getTwitterInstance();
			int firstIndex = hashtag.indexOf('#');
			Query query;
			if(firstIndex != 0) {
				hashtag = '#' + hashtag;
			}
			query = new Query(hashtag);
			query.setCount(100);
			query.setResultType(ResultType.recent);
		    QueryResult result = twitter.search(query);
		    List<Status> tweets = result.getTweets(); 
		    for (Status tweet : tweets) {
		    	Pessoa pessoa = new Pessoa();
		    	pessoa.setUserId(tweet.getUser().getId());
		    	pessoa.setHashTag(hashtag);
		    	pessoa.setUserName(tweet.getUser().getName());
		    	pessoa.setMessage(tweet.getText());
		    	pessoa.setFollower(tweet.getUser().getFollowersCount());
		    	tweetsrHashTags.add(pessoa);
		    } 	
		}catch(TwitterException te){
			LOGGER.error("Falha na consulta das HashTags",te);
		}
		tweetsRepository.saveAll(tweetsrHashTags);
		return(tweetsrHashTags);
	}
}
