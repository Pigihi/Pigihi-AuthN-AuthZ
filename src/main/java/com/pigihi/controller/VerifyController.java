/**
 * 
 */
package com.pigihi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	//TODO Email verification
	@GetMapping
	public String verifyEmail(@RequestParam("emailToken") String emailToken) {
		
	}
	
	//TODO Mobile verification
	@GetMapping
	public String verifyMobile(@RequestParam("mobileToken") String mobileToken) {
		
	}

}
