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
 * @author Ashish Sam T George
 *
 */
public class VerificationServiceImpl implements VerificationServiceInterface {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public VerificationTokenEntity saveInDB(UserAuthEntity user, String emailToken, String mobileToken) {
		VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, emailToken, mobileToken);
		verificationTokenEntity = verificationTokenRepository.save(verificationTokenEntity);
		return verificationTokenEntity;
	}

}
