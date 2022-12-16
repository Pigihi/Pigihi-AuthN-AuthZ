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
 * Implementation class for {@link UserRoleServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
public class UserRoleServiceImpl implements UserRoleServiceInterface {
	
	@Autowired
	private UserRegistrationServiceInterface userRegistrationService;
	
	/**
	 * @param email Passed as a string parameter
	 * @param mobile Passed as a string parameter
	 * @return Returns an integer code representing the role of the user <br>
	 * Supported codes are: <br>
	 * 000 - User does not exist with that details <br>
	 * 222 - User with role "CUSTOMER" exist with that details <br>
	 * 555 - User with role "SHOP" exist with that details <br>
	 * 777 - User with role "CUSTOMER_AND_SHOP" exist with that details <br>
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see UserAuthEntity
	 * @see UserRoleEnum
	 * 
	 */
	@Override
	public int findRoleCode(String email, String mobile) {

		//TODO If one user has this email and another user has this mobile, which user to return
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
