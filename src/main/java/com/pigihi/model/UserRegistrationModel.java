/**
 * 
 */
package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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

}
