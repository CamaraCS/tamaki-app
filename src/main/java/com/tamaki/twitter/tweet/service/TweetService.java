package com.tamaki.twitter.tweet.service;

import static org.springframework.data.mongodb.core.query.Query.query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.tamaki.twitter.domain.Pessoa;
import com.tamaki.twitter.repository.ITweetStoreRepository;
import com.tamaki.twitter.tweet.service.TweetStoreService;

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
@EnableMongoRepositories(basePackageClasses = ITweetStoreRepository.class)
public class TweetService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TweetService.class);
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
    private ITweetStoreRepository tweetRepository;
	
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
		    	pessoa.setId(new BigInteger(64, new Random()));
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
		recordHashTag(tweetsrHashTags, hashtag);
		return(tweetsrHashTags);
	}
	
	private List<String> listTag() {
		List<String> tags = new ArrayList<String>();
		tags.add("#cloud");
		tags.add("#container");
		tags.add("#devops");
		tags.add("#aws");
		tags.add("#microservices");
		tags.add("#docker");
		tags.add("#openstack");
		tags.add("#automation");
		tags.add("#gcp");
		tags.add("#azure");
		tags.add("#istio");
		tags.add("#sre");
	return tags;
	}
	
	public void recordHashTag(List<Pessoa> tweetsrHashTags, String hashtag) {
		List<String> tags = new ArrayList<String>();
		tags = listTag();
		boolean store = false;
		for (String valor : tags) {
			if (valor.equals(hashtag)) {
				store = true;
				break;
			}
		}
		if(store) {
			mongoOperations.remove(query(Criteria.where("hashTag").is(hashtag)),"tweets");
			System.out.println(hashtag);
			tweetRepository.saveAll(tweetsrHashTags);
		}
	}
	public List<Pessoa> listDBHashTag(String hashtag) {
		return mongoOperations.find(query(Criteria.where("hashTag").is("#" + hashtag)), Pessoa.class, "tweets");
	}
	public List<Pessoa> listDBTopHashTag(String hashtag) {
		org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
		query.addCriteria(Criteria.where("hashTag").is("#" + hashtag));
		query.limit(5);
		query.with(new Sort(Sort.Direction.DESC, "follower"));
		return mongoOperations.find(query, Pessoa.class, "tweets");	
	}
}
