/**
 * 
 */
package com.pigihi.service.interfaces;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.VerificationTokenQueueModel;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Ashish Sam T George
 *
 */
public interface PublishVerificationTokenServiceInterface {

	Boolean publish(UserAuthEntity user, HttpServletRequest request);

	void publishToQueue(String string);

}
