/**
 * 
 */
package com.pigihi.service.interfaces;

import com.pigihi.entity.UserAuthEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface UserLoginServiceInterface {

	UserAuthEntity findByEmail(String email);

}
