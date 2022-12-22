/**
 * 
 */
package com.pigihi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.UserAuthEntity;

/**
 * Repository class for user authentication and authorization details
 * 
 * @author Ashish Sam T George
 *
 */
public interface UserAuthRepository extends MongoRepository<UserAuthEntity, String> {
	
	@Query("{$or: [{email: ?0}, {mobile: ?1}]}")
	UserAuthEntity findByEmailOrMobile(String email, String mobile);

	UserAuthEntity findByEmail(String email);

}
