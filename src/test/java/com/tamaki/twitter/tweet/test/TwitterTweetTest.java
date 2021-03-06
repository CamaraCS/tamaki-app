package com.tamaki.twitter.tweet.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.tamaki.twitter.App;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class TwitterTweetTest {
	
	@Inject
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetTweets() throws Exception 
	{
		mockMvc.perform(get("/tweets/hashtags/devops")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isOk());
	}
	@Test
	public void testGetTweetsInvalid() throws Exception 
	{
		mockMvc.perform(get("/tweets/hashtags/#devops")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isNotFound());
	}
	@Test
	public void testGetListTweets() throws Exception 
	{
		mockMvc.perform(get("/tweets/list/devops")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isOk());
	}
	@Test
	public void testGetListTweetsInvalid() throws Exception 
	{
		mockMvc.perform(get("/tweets/list/aa")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isNotFound());
	}
	@Test
	public void testGetTopListTweets() throws Exception 
	{
		mockMvc.perform(get("/tweets/toplist/devops")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isOk());
	}
	@Test
	public void testGetTopListTweetsInvalid() throws Exception 
	{
		mockMvc.perform(get("/tweets/toplist/aa")
	      .accept(MediaType.APPLICATION_JSON_UTF8))
	      .andExpect(status().isNotFound());
	}
}
