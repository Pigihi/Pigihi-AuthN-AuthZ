/**
 * 
 */
package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRegistrationModel;
import com.pigihi.service.interfaces.PublishVerificationTokenServiceInterface;
import com.pigihi.service.interfaces.UserRegistrationServiceInterface;
import com.pigihi.service.interfaces.UserRoleServiceInterface;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Ashish Sam T George
 *
 */

@CrossOrigin("*")
@RestController
public class RegistrationController {
	
	@Autowired
	private UserRegistrationServiceInterface userRegistrationService;
	
	@Autowired
	private UserRoleServiceInterface userRoleService;
	
	@Autowired
	private PublishVerificationTokenServiceInterface publishVerificationTokenService;
	
	public String registerUser(@RequestBody UserRegistrationModel userRegistrationModel,
			HttpServletRequest request) {
		
		//TODO Sanitize Input Data
		
		String email = userRegistrationModel.getEmail();
		String mobile = userRegistrationModel.getMobile();
		int roleCode = userRoleService.findRoleCode(email, mobile);
		Gson gson = new Gson();
		if(roleCode == 000) {
			UserAuthEntity user = userRegistrationService.registerUser(userRegistrationModel);
			Boolean state = publishVerificationTokenService.publish(user, request);
						
			//TODO What if publishing failed
			int status = 999;
			return gson.toJson(status);
		}
		else {
			//TODO How about using an HTTP response code other than 200
			int status = roleCode;
			return gson.toJson(status);
		}
		
	}

}

//TODO Create endpoints for changing role and status of user and so on

//TODO No registration for admin. Login for admin

//TODO What about a batch program to clear the unverified users from the DB within a certain period of time

//TODO Write Unit Tests

//TODO Where and How should I catch exceptions
