package com.tamaki.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tamaki.twitter.tweet.service.TweetStoreService;

/**
 *
 * @author Renato.T.Tamaki
*/

@SpringBootApplication
public class App {

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}
}
