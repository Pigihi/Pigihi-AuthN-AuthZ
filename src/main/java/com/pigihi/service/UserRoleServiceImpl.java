/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRoleEnum;
import com.pigihi.service.interfaces.UserRegistrationServiceInterface;
import com.pigihi.service.interfaces.UserRoleServiceInterface;

/**
 * @author Ashish Sam T George
 *
 */
public class UserRoleServiceImpl implements UserRoleServiceInterface {
	
	@Autowired
	private UserRegistrationServiceInterface userRegistrationService;
	
	/**
	 * @param email Passed as a string parameter
	 * @param mobile Passed as a string parameter
	 * @return int Returns an integer code 
	 */
	@Override
	public int findRoleCode(String email, String mobile) {
		// TODO Auto-generated method stub
		UserAuthEntity user = userRegistrationService.findExistingUser(email, mobile);
		if(user != null) {
			return user.getRole().roleCode;
		}
		else {
			// 000 indicates that there is no user with that email or mobile
			return 000;
		}
	}

}
