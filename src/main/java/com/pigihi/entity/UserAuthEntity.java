/**
 * 
 */
package com.pigihi.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pigihi.model.StatusEnum;
import com.pigihi.model.UserRoleEnum;

import lombok.Data;

/**
 * User entity class
 * 
 * @author Ashish Sam T George
 *
 */

@Document(collection = "auth_collection")
@Data
public class UserAuthEntity {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String mobile;
	private String password;
	private UserRoleEnum role;
	private StatusEnum status = StatusEnum.DISABLED;
	
	//TODO Granted Authorities

}
