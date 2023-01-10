/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserLoginModel;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserAuthEntity findByEmail(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		return user;
	}

	@Override
	public Authentication login(UserLoginModel userLoginModel) throws Exception {
		Authentication usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(
						userLoginModel.getEmail(), userLoginModel.getPassword());
		try {
			usernamePasswordAuthenticationToken = 
					authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			return usernamePasswordAuthenticationToken;
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ", e);
		}
		
	}

}
