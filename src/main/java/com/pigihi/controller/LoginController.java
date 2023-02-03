/**
 * 
 */
package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserLoginModel;
import com.pigihi.service.UserStatusResponseGenerator;
import com.pigihi.service.interfaces.UserLoginServiceInterface;

/**
 * Controller class for loggin in a customer or shop
 * 
 * @author Ashish Sam T George
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/login/user")
public class LoginController {
	
	@Autowired
	private UserLoginServiceInterface userLoginService;
	
	@Autowired
	private UserStatusResponseGenerator userStatusResponseGenerator;

	//TODO Get email and password from request
	//TODO Call userLoginService to login the user
	//TODO Call checkUserStatusService to check whether the user is disabled
	//TODO Return JWT if enabled, otherwise return corresponding response code
	
	@PostMapping
	public String loginUser(@RequestBody UserLoginModel userLoginModel) throws Exception {
		Authentication authenticatedUsernamePasswordAuthenticationToken = 
				userLoginService.login(userLoginModel);
		UserAuthEntity user = (UserAuthEntity) authenticatedUsernamePasswordAuthenticationToken
												.getPrincipal();
		String response = userStatusResponseGenerator.generate(user);
		return response;
	}
}
