/**
 * 
 */
package com.pigihi.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
	public List<String> getRolesFromJwt(@RequestParam String jwt) {
		
		List<String> authorities = new ArrayList<String>();
		// Perform real validation of JWT
		if(jwtUtility.validateToken(jwt)) {
			Claims body = jwtUtility.getBody(jwt);
			List<LinkedHashMap<String, String>> authorityMapList = (List<LinkedHashMap<String,String>>) body.get("authorities");
			for (LinkedHashMap<String, String> authorityMap : authorityMapList) {
				authorities.add(authorityMap.get("authority"));
			}
			System.out.println("Authorities from JWT: " + authorities); 
		}
		
		return authorities;
	}

}
