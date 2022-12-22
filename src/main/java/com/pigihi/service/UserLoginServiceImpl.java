/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.repository.UserAuthRepository;
import com.pigihi.service.interfaces.UserLoginServiceInterface;

/**
 * Implementation class of User Login Service
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class UserLoginServiceImpl implements UserLoginServiceInterface {

	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Override
	public UserAuthEntity findByEmail(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		return user;
	}

}
