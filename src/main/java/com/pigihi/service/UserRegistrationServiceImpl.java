/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRegistrationModel;
import com.pigihi.repository.UserAuthRepository;
import com.pigihi.service.interfaces.UserRegistrationServiceInterface;

/**
 * @author Ashish Sam T George
 *
 */
public class UserRegistrationServiceImpl implements UserRegistrationServiceInterface {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthRepository userAuthRepository;

	/**
	 * @param email Passed as a string parameter
	 * @param mobile Passed as a string parameter
	 * @return int Returns an integer code 
	 */
	//TODO Only Admin should be able to call this directly.
	@Override
	public UserAuthEntity findExistingUser(String email, String mobile) {
		
		// Returns null if no user is found
		UserAuthEntity user = userAuthRepository.findByEmailOrMobile(email, mobile);
		return user;
	}

	@Override
	public UserAuthEntity registerUser(UserRegistrationModel userRegistrationModel) {
		// TODO Auto-generated method stub
		UserAuthEntity user = new UserAuthEntity();
		user.setFullName(userRegistrationModel.getFullName());
		user.setEmail(userRegistrationModel.getEmail());
		user.setMobile(userRegistrationModel.getMobile());
		user.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
		user.setRole(userRegistrationModel.getRole());
		
		userAuthRepository.save(user);
		return user;
		
	}

}
