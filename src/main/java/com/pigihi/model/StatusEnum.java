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
	
	ADMIN_DISABLED,
	USER_DISABLED,
	ENABLED,
	DISABLED
	//TODO What about half-verification, like only email or only mobile. Also, update the javadoc after modification

}
