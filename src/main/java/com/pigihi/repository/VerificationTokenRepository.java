/**
 * 
 */
package com.pigihi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pigihi.entity.VerificationTokenEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface VerificationTokenRepository extends MongoRepository<VerificationTokenEntity, String> {

	
	
}
