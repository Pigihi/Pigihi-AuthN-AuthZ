/**
 * 
 */
package com.pigihi.service.interfaces;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.entity.VerificationTokenEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface VerificationServiceInterface {

	VerificationTokenEntity saveInDB(UserAuthEntity user, String emailToken, String mobileToken);

	UserAuthEntity verifyEmail(String emailToken);

	UserAuthEntity saveToUserCollection(UserAuthEntity user);

}
