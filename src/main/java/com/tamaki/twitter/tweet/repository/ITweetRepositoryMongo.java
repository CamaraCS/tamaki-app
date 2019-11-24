package com.tamaki.twitter.tweet.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Renato.T.Tamaki
 */

public interface ITweetRepositoryMongo extends MongoRepository<BigInteger, String User, String Messange>{

	@Query(value = "{ 'idCliente' : ?#{[0]} }", delete = true)
	public void deleteById(BigInteger idCliente);
}
