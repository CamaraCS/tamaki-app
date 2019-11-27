package com.tamaki.twitter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.tamaki.twitter.domain.Pessoa;

public interface ITweetStoreRepository extends MongoRepository<Pessoa, String> {

//	@DeleteQuery
//	List<Pessoa> deleteByHashTag (@Param(value = "hashTag") String hashTag);
//	Long deletePessoaByHashTag(String hashTag);    
//	List<Pessoa> listTweets(@Param(value = "hashTag") String hashTag);
}
