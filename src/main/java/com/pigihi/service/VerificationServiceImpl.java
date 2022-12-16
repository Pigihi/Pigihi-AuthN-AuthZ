/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.entity.VerificationTokenEntity;
import com.pigihi.repository.VerificationTokenRepository;
import com.pigihi.service.interfaces.VerificationServiceInterface;

/**
 * Implementation class of {@link VerificationServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
public class VerificationServiceImpl implements VerificationServiceInterface {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	/**
	 * Store the tokens
	 * 
	 * @param user {@link UserAuthEntity} object
	 * @param emailToken Email token as string
	 * @param mobileToken Mobile token as string
	 * @return Returns {@link VerificationTokenEntity} object
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public VerificationTokenEntity saveInDB(UserAuthEntity user, String emailToken, String mobileToken) {
		VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, emailToken, mobileToken);
		verificationTokenEntity = verificationTokenRepository.save(verificationTokenEntity);
		return verificationTokenEntity;
	}

}
