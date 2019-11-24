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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.tamaki.twitter.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
//@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TwitterTweetTest {
	
	@Inject
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
//	@Test
//	public void testGetAllpolls() throws Exception{
//		mockMvc.perform(get("/tweets/hashtags/devops"))
//			.andExpect(status().isOk());
//	}
	@Test
	public void testGetAllpolls() throws Exception 
	{
		mockMvc.perform(get("/tweets/hashtags/devops")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	 
}
