/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.StatusEnum;
import com.pigihi.repository.UserAuthRepository;

/**
 * Service class for user related operations
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	public UserAuthEntity findByEmail(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		return user;
	}

	/**
	 * Change status of the user to USER_DISABLED
	 * 
	 * @param email
	 * @return UserAuthEntity
	 * 
	 * @see UserAuthEntity
	 * @see StatusEnum
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	// Can be invoked by customer and shop
	public UserAuthEntity disableUser(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
//		user.setStatus(StatusEnum.USER_DISABLED);
		user.setEnableStatus(StatusEnum.USER_DISABLED);
		UserAuthEntity disabledUser = userAuthRepository.save(user);
		return disabledUser;
	}

	/**
	 * Change status of the user to ENABLED
	 * 
	 * @param email
	 * @return UserAuthEntity
	 * 
	 * @see UserAuthEntity
	 * @see StatusEnum
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public UserAuthEntity enableUser(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
//		user.setStatus(StatusEnum.ENABLED);
		user.setEnableStatus(StatusEnum.ENABLED);
		UserAuthEntity enabledUser = userAuthRepository.save(user);
		return enabledUser;
	}

	public UserAuthEntity disableUserByAdmin(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		user.setEnableStatus(StatusEnum.ADMIN_DISABLED);
		UserAuthEntity disabledUser = userAuthRepository.save(user);
		return disabledUser;
	}

	public UserAuthEntity enableUserByAdmin(String email) {
		UserAuthEntity user = userAuthRepository.findByEmail(email);
		user.setEnableStatus(StatusEnum.ENABLED);
		UserAuthEntity enabledUser = userAuthRepository.save(user);
		return enabledUser;
	}

}
