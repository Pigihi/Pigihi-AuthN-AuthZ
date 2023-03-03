/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.StatusEnum;
import com.pigihi.utility.JWTUtility;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class UserStatusResponseGenerator {
	
	@Autowired
	private JWTUtility jwtUtility;

	public String generate(UserAuthEntity user) {
		String token = null;
//		if(user.getStatus() == StatusEnum.ENABLED) {
		if(user.getEnableStatus() == StatusEnum.ENABLED) {
			token = jwtUtility.generateToken(user);
		}
//		int statusCode = user.getStatus().userStatusCode;
		String statusCode = user.getEnableStatus().toString();
		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("statusCode", statusCode);
		jsonResponse.addProperty("token", token);
		return jsonResponse.toString();
	}

}
