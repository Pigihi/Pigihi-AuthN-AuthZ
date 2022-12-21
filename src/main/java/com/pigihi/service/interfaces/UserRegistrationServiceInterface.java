/**
 * 
 */
package com.pigihi.service.interfaces;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRegistrationModel;

/**
 * @author Ashish Sam T George
 *
 */
public interface UserRegistrationServiceInterface {

	UserAuthEntity findExistingUser(String email, String mobile);

	UserAuthEntity registerUser(UserRegistrationModel userRegistrationModel);

}
