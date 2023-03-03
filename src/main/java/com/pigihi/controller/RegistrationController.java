package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRegistrationModel;
import com.pigihi.model.UserRoleEnum;
import com.pigihi.service.interfaces.PublishVerificationTokenServiceInterface;
import com.pigihi.service.interfaces.UserRegistrationServiceInterface;
import com.pigihi.service.interfaces.UserRoleServiceInterface;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for registering a customer or shop
 * 
 * @author Ashish Sam T George
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/auth/register/user")
public class RegistrationController {
	
	@Autowired
	private UserRegistrationServiceInterface userRegistrationService;
	
	@Autowired
	private UserRoleServiceInterface userRoleService;
	
	@Autowired
	private PublishVerificationTokenServiceInterface publishVerificationTokenService;
	
	/**
	 * Saves the details of the user only if the email and mobile do not already exist <br>
	 * After creating user, verification tokens are generated and pushed to message broker
	 * 
	 * 
	 * @param userRegistrationModel Registration input received from the user
	 * @param request An HttpServletRequest object
	 * @return JSON string is returned in the form of <br>
	 * { <br>
	 * 	status: xxx <br>
	 * } <br>
	 * Supported codes are: <br>
	 * 000 - User does not exist with that details <br>
	 * 222 - User with role "CUSTOMER" exist with that details <br>
	 * 555 - User with role "SHOP" exist with that details <br>
	 * 777 - User with role "CUSTOMER_AND_SHOP" exist with that details <br>
	 * 
	 * @see UserRegistrationModel
	 * @see UserRoleEnum
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@PostMapping
	public String registerUser(@RequestBody UserRegistrationModel userRegistrationModel,
			HttpServletRequest request) {
		
		//TODO Sanitize Input Data
		
		String email = userRegistrationModel.getEmail();
		String mobile = userRegistrationModel.getMobile();
//		int roleCode = userRoleService.findRoleCode(email, mobile);
		UserRoleEnum roleCode = userRoleService.findRoleCode(email, mobile);
		Gson gson = new Gson();
//		if(roleCode == 000) {
		if(roleCode == null) {
			UserAuthEntity user = userRegistrationService.registerUser(userRegistrationModel);
			Boolean state = publishVerificationTokenService.publish(user, request);
						
			//TODO What if publishing failed
			int status = 999;
			return gson.toJson(status);
		}
		else {
			//TODO How about using an HTTP response code other than 200
//			int status = roleCode;
			UserRoleEnum status = roleCode;
			return gson.toJson(status);
		}
		
	}

}

//TODO Create endpoints for changing role and status of user and so on

//TODO No registration for admin. Login for admin

//TODO What about a batch program to clear the unverified users from the DB within a certain period of time

//TODO Write Unit Tests

//TODO Where and How should I catch exceptions
