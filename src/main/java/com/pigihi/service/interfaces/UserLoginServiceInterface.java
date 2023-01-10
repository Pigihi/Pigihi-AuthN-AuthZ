/**
 * 
 */
package com.pigihi.service.interfaces;

import org.springframework.security.core.Authentication;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserLoginModel;

/**
 * @author Ashish Sam T George
 *
 */
public interface UserLoginServiceInterface {

	UserAuthEntity findByEmail(String email);

	Authentication login(UserLoginModel userLoginModel) throws Exception;

}
