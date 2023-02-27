/**
 * 
 */
package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.utility.JWTUtility;

import io.jsonwebtoken.Claims;

/**
 * Controller class for authorizing user
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/authorize/user")
public class AuthorizeController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@GetMapping
	public String getRolesFromJwt(@RequestParam String jwt) {
		
		// Perform real validation of JWT
		if(jwtUtility.validateToken(jwt)) {
			Claims body = jwtUtility.getBody(jwt);
			 body.get("authorities");
		}
		
		return "Implementation not complete";
	}

}
