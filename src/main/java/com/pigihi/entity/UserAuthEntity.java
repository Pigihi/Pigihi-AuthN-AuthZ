/**
 * 
 */
package com.pigihi.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pigihi.model.PrivilegeEnum;
import com.pigihi.model.StatusEnum;
import com.pigihi.model.UserRoleEnum;

import lombok.Data;

/**
 * User entity class
 * 
 * @author Ashish Sam T George
 *
 */

@Document(collection = "authentication_collection")
@Data
public class UserAuthEntity {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String mobile;
	private String password;
	private UserRoleEnum role;
	private List<PrivilegeEnum> privileges;
//	private StatusEnum status = StatusEnum.DISABLED;
	private StatusEnum enableStatus = StatusEnum.DISABLED;
	
	//TODO Granted Authorities
	public Collection<? extends GrantedAuthority> getAuthorities(List<UserRoleEnum> role, List<PrivilegeEnum> privileges){
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRoleEnum singleRole : role) {
			authorities.add(new SimpleGrantedAuthority(singleRole.toString()));
		}
		for(PrivilegeEnum privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege.toString()));
		}
		return authorities;
	}

}
