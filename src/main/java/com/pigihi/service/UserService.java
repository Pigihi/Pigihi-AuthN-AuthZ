/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.repository.UserAuthRepository;

/**
 * Service class for user related operations
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	public UserAuthEntity findByEmail(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		return user;
	}

}
