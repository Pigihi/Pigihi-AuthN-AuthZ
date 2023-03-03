/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRegistrationModel;
import com.pigihi.repository.UserAuthRepository;
import com.pigihi.service.interfaces.UserRegistrationServiceInterface;

/**
 * Implementation class for {@link UserRegistrationServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationServiceInterface {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthRepository userAuthRepository;

	/**
	 * Find any existing user with the given email or mobile
	 * 
	 * @param email Passed as a string parameter
	 * @param mobile Passed as a string parameter
	 * @return Returns {@link UserAuthEntity} object
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	//TODO Only Admin should be able to call this directly.
	@Override
	public UserAuthEntity findExistingUser(String email, String mobile) {
		
		// Returns null if no user is found
		UserAuthEntity user = userAuthRepository.findByEmailOrMobile(email, mobile);
		return user;
	}

	/**
	 * Save the user with the provided details
	 * 
	 * @param {@link UserRegistrationModel}
	 * @return {@link UserAuthEntity}
	 * 
	 * @author Ashish Sam T George
	 */
	@Override
	public UserAuthEntity registerUser(UserRegistrationModel userRegistrationModel) {

		UserAuthEntity user = new UserAuthEntity();
		user.setFullName(userRegistrationModel.getFullName());
		user.setEmail(userRegistrationModel.getEmail());
		user.setMobile(userRegistrationModel.getMobile());
		user.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
		user.setRole(userRegistrationModel.getRole());
		user.setPrivileges(userRegistrationModel.getPrivileges());
		
		userAuthRepository.save(user);
		return user;
		
	}

}
