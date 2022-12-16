/**
 * 
 */
package com.pigihi.model;

/**
 * @author Ashish Sam T George
 *
 */
public enum UserRoleEnum {
	
	CUSTOMER(222),
	SHOP(555),
	CUSTOMER_AND_SHOP(777);
	
	public final int roleCode;
	
	UserRoleEnum(int roleCode) {
		this.roleCode = roleCode;
	}

}
