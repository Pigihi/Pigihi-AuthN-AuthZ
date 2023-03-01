/**
 * 
 */
package com.pigihi.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.entity.VerificationTokenEntity;
import com.pigihi.event.RegistrationCompleteEvent;
import com.pigihi.model.VerificationTokenQueueModel;
import com.pigihi.service.interfaces.GenerateTokenServiceInterface;
import com.pigihi.service.interfaces.PublishVerificationTokenServiceInterface;
import com.pigihi.service.interfaces.VerificationServiceInterface;
import com.pigihi.utility.MessageContentGenerator;

/**
 * Event Listener class for {@link RegistrationCompleteEvent} event
 * 
 * @author Ashish Sam T George
 *
 */
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	@Autowired
	private GenerateTokenServiceInterface generateTokenService;
	
	@Autowired
	private VerificationServiceInterface verificationService;
	
	@Autowired
	private MessageContentGenerator messageContentGenerator;
	
	@Autowired
	private PublishVerificationTokenServiceInterface publishVerificationTokenService;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {

		UserAuthEntity user = event.getUser();
		VerificationTokenQueueModel verificationTokenQueueModel = new VerificationTokenQueueModel();
		
		String emailToken = generateTokenService.getEmailToken();
		String mobileToken = generateTokenService.getMobileToken();

		VerificationTokenEntity verificationTokenEntity = verificationService.saveInDB(user, emailToken, mobileToken);

		verificationTokenQueueModel.setEmailSendTo(user.getEmail());
		verificationTokenQueueModel.setEmailSubject(messageContentGenerator.emailSubject());
		verificationTokenQueueModel.setEmailBody(messageContentGenerator.emailBody(user.getFullName(),
				event.getBaseUrl(), emailToken));
		
		verificationTokenQueueModel.setMobileSendTo(user.getMobile());
		verificationTokenQueueModel.setMobileSubject(messageContentGenerator.mobileSubject());
		verificationTokenQueueModel.setMobileBody(messageContentGenerator.mobileBody(user.getFullName(),
				event.getBaseUrl(), mobileToken));
		
		Gson gson = new Gson();
		publishVerificationTokenService.publishToQueue(gson.toJson(verificationTokenQueueModel));
		
	}
	
}
