/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.service.interfaces.VerificationServiceInterface;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class for verifying email and mobile of a user
 * 
 * @author Ashish Sam T George
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/register/verify")
public class VerifyController {
	
	@Autowired
	private VerificationServiceInterface verificationService;
	
	/**
	 * Verifies the email token. 
	 * If the token is valid, then the {@link StatusEnum} of the corresponding user is updated.
	 * Then, the API is called for storing user details to the corresponding user collection.
	 * 
	 * @param emailToken Token for verifying email
	 * @return Returns 
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	//TODO Email verification
	@GetMapping("email")
	public String verifyEmail(@RequestParam("emailToken") String emailToken,
			HttpServletResponse response) throws IOException {
		
		UserAuthEntity user = verificationService.verifyEmail(emailToken);
		if(user != null) {
			user = verificationService.saveToUserCollection(user);
			response.sendRedirect("");
			return "Success"; //TODO Check whether axios can get the string even in a redirection response
		}
		else {
			response.sendRedirect("");
			return "Failure"; //TODO Check whether axios can get the string even in a redirection response
		}
		
	}
	
	/**
	 * Verifies the mobile token. 
	 * If the token is valid, then the {@link StatusEnum} of the corresponding user is updated.
	 * Then, the API is called for storing user details to the corresponding user collection.
	 * 
	 * @param mobileToken Token for verifying mobile
	 * @return Returns 
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	//TODO Mobile verification
	@GetMapping("mobile")
	public String verifyMobile(@RequestParam("mobileToken") String mobileToken) {
		//TODO Write logic for verifying mobile (similar to that of email)
		return null;
	}

}
