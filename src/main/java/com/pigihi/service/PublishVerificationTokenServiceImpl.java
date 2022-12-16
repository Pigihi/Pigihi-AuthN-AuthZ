/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.event.RegistrationCompleteEvent;
import com.pigihi.model.VerificationTokenQueueModel;
import com.pigihi.service.interfaces.PublishVerificationTokenServiceInterface;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Ashish Sam T George
 *
 */
public class PublishVerificationTokenServiceImpl implements PublishVerificationTokenServiceInterface {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public Boolean publish(UserAuthEntity user, HttpServletRequest request) {

		String baseUrl = generateVerificationUrl(request);
		RegistrationCompleteEvent registrationCompleteEvent = new RegistrationCompleteEvent(user, baseUrl);
		publisher.publishEvent(registrationCompleteEvent);
		return true;
		
	}
	
	private String generateVerificationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	@Override
	public void publishToQueue(String QueueJson) {
		System.out.println("Queue JSON: " + QueueJson);
		//TODO Write logic for pushing to queue
		
	}

}
