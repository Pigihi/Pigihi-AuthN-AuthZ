/**
 * 
 */
package com.pigihi.service.interfaces;

import com.pigihi.model.UserRoleEnum;

/**
 * @author Ashish Sam T George
 *
 */
public interface UserRoleServiceInterface {

//	int findRoleCode(String email, String mobile);
	
	UserRoleEnum findRoleCode(String email, String mobile);

}
