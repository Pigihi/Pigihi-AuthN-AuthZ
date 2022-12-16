/**
 * 
 */
package com.pigihi.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pigihi.service.interfaces.GenerateTokenServiceInterface;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class MessageContentGenerator {
	
	@Autowired
	private GenerateTokenServiceInterface generateTokenService;

	public String emailBody(String fullName, String baseUrl, String emailToken) {
		// TODO How about getting the message body from another file like .env or something
		String emailUrl = generateTokenService.getEmailTokenUrl(baseUrl, emailToken);
		String body = "Hi " + fullName + ",\n" 
				+ "Welcome to Pigihi Family. \n"
				+ "Your email Verification token is: \n"
				+ emailToken + "\n"
				+ "You can also click this link to verify: " + emailUrl;
		return body;
	}

	public String mobileBody(String fullName, String baseUrl, String mobileToken) {
		String mobileUrl = generateTokenService.getMobileTokenUrl(baseUrl, mobileToken);
		String body = "Hi " + fullName + ",\n" 
				+ "Welcome to Pigihi Family. \n"
				+ "Your mobile Verification token is: \n"
				+ mobileToken + "\n"
				+ "You can also click this link to verify: " + mobileUrl;
		return body;
	}

	public String emailSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	public String mobileSubject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
