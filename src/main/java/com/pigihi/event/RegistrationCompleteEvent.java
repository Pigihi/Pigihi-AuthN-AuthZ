/**
 * 
 */
package com.pigihi.event;

import org.springframework.context.ApplicationEvent;

import com.pigihi.entity.UserAuthEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ashish Sam T George
 *
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
	
	private UserAuthEntity user;
	private String baseUrl;
	
	public RegistrationCompleteEvent(UserAuthEntity user, String baseUrl) {
		super(user);
		this.user = user;
		this.baseUrl = baseUrl;
	}

}
