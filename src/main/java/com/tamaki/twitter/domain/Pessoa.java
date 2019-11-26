package com.tamaki.twitter.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Pessoa {

    private long userId;
	private String hashTag;
    private String userName;
    private String message;
    private int follower;

   public Pessoa() {
   }

   public Pessoa(long userId, String hashTag, String userName, String message, int follower) {
	   this.userId = userId;
	   this.hashTag = hashTag;
       this.userName = userName;
       this.message = message;
       this.follower = follower;
   }
  
   public long getUserId() {
       return this.userId;
   }
   
   public void setUserId(long userID) {
       this.userId = userID;
   }
   public String getHashTag() {
       return this.hashTag;
   }
   
   public void setHashTag(String hashTag) {
       this.hashTag = hashTag;
   }

   public String getUserName() {
       return this.userName;
   }
   
   public void setUserName(String userName) {
       this.userName = userName;
   }

   public String getMessage() {
       return this.message;
   }
   
   public void setMessage(String message) {
       this.message = message;
   }

   public int getFollower() {
       return this.follower;
   }
   
   public void setFollower (int follower) {
       this.follower = follower;
   }
}