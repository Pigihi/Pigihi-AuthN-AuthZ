package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.service.UserService;

/**
 * Controller class for handling user operations
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Disable an already existing user
	 * 
	 * @param email
	 * @return JSON string
	 * 
	 * @see UserAuthEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@DeleteMapping
	public String disableUser(@RequestParam String email) {

		UserAuthEntity user = userService.disableUser(email);
		String jsonUser = convertToJson(user);
		return jsonUser;
		
	}
	
	/**
	 * Enable an already existing user
	 * 
	 * @param email
	 * @return JSON string
	 * 
	 * @see UserAuthEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@PatchMapping
	public String enableUser(@RequestParam String email) {
		UserAuthEntity user = userService.enableUser(email);
		String jsonUser = convertToJson(user);
		return jsonUser;
	}
	
	private String convertToJson(UserAuthEntity userEntity) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(userEntity);
		return jsonString;
	}

}
