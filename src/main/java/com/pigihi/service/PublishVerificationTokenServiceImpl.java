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
 * Implementation class of {@link PublishVerificationTokenServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
public class PublishVerificationTokenServiceImpl implements PublishVerificationTokenServiceInterface {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * Publishes a {@link RegistrationCompleteEvent} event
	 * 
	 * @param user {@link UserAuthEntity} object
	 * @param request
	 * @return Returns true
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public Boolean publish(UserAuthEntity user, HttpServletRequest request) {

		String baseUrl = generateVerificationUrl(request);
		RegistrationCompleteEvent registrationCompleteEvent = new RegistrationCompleteEvent(user, baseUrl);
		publisher.publishEvent(registrationCompleteEvent);
		return true;
		
	}
	
	/**
	 * Generates the base URL using the received {@link HttpServletRequest}
	 * 
	 * @param request
	 * @return Returns the base URL as string
	 * 
	 * @author Ashish Sam T George
	 */
	private String generateVerificationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/**
	 * Push the JSON data to the queue
	 * 
	 * @param QueueJson JSON data to be pushed to the queue
	 * 
	 * @author Ashish Sam T George
	 */
	//TODO Most likely, need to move this logic from this class
	@Override
	public void publishToQueue(String QueueJson) {
		System.out.println("Queue JSON: " + QueueJson);
		//TODO Write logic for pushing to queue
		
	}

}
