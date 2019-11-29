package com.tamaki.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Renato.T.Tamaki
*/

@SpringBootApplication
//@ComponentScan({ "com.tamaki.twitter.tweet.controller", "com.tamaki.twitter.tweet.service", "com.tamaki.twitter.tweet.exception" })
public class App {

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}
}
