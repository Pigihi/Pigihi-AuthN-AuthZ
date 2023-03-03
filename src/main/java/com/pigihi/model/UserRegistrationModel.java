/**
 * 
 */
package com.pigihi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for receiving registration details from user
 * 
 * @author Ashish Sam T George
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {
	
	private String fullName;
	
	private String email;
	
	private String mobile;
	
	private String password;
	
	private UserRoleEnum role;
	
	private List<PrivilegeEnum> privileges;

}
