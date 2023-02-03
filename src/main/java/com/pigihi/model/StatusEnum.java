/**
 * 
 */
package com.pigihi.model;

/**
 * Enum containing the statuses a user can have <br>
 * ADMIN_DISABLED - The user is disabled by the admin. Only an admin can enable the user back. <br>
 * USER_DISABLED - The user is disabled by the user itself. Can enable by re-verification of email and mobile. <br>
 * ENABLED - The user is enabled after successful verification of email and mobile. <br>
 * DISABLED - The user is created but not yet verified email or mobile. <br>
 * 
 * @author Ashish Sam T George
 *
 */
public enum StatusEnum {
	
	ADMIN_DISABLED(760),
	USER_DISABLED(761),
	MOBILE_VERIFIED(770),
	EMAIL_VERIFIED(771),
	ENABLED(779),
	DISABLED(766);
	//TODO What about half-verification, like only email or only mobile. Also, update the javadoc after modification

	public final int userStatusCode;
	
	StatusEnum(int userStatusCode){
		this.userStatusCode = userStatusCode;
	}

}
