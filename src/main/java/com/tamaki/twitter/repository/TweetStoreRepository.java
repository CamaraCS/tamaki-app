package com.tamaki.twitter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tamaki.twitter.domain.Pessoa;

public interface TweetStoreRepository extends MongoRepository<Pessoa, String> {

}
