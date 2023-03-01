/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pigihi.entity.UserAuthEntity;
import com.pigihi.entity.VerificationTokenEntity;
import com.pigihi.model.StatusEnum;
import com.pigihi.model.UserRoleEnum;
import com.pigihi.repository.UserAuthRepository;
import com.pigihi.repository.VerificationTokenRepository;
import com.pigihi.service.interfaces.VerificationServiceInterface;

/**
 * Implementation class of {@link VerificationServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class VerificationServiceImpl implements VerificationServiceInterface {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private UserAuthRepository userAuthRepository;

	/**
	 * Store the tokens
	 * 
	 * @param user {@link UserAuthEntity} object
	 * @param emailToken Email token as string
	 * @param mobileToken Mobile token as string
	 * @return Returns {@link VerificationTokenEntity} object
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public VerificationTokenEntity saveInDB(UserAuthEntity user, String emailToken, String mobileToken) {
		VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, emailToken, mobileToken);
		verificationTokenEntity = verificationTokenRepository.save(verificationTokenEntity);
		return verificationTokenEntity;
	}

	/**
	 * Verifies email token
	 * 
	 * @param emailToken Token for verifying email
	 * @return Returns {@link UserAuthEntity} object <br>
	 * 		 Returns null if the token does not exist or user does not exist or token is expired
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public UserAuthEntity verifyEmail(String emailToken) {
		
		VerificationTokenEntity verificationTokenEntity = verificationTokenRepository.findByEmailToken(emailToken);
		if(verificationTokenEntity == null) {
			return null;
		}
		UserAuthEntity user = verificationTokenEntity.getUser();
		// When the user does not exist(when admin deletes the user or something like that) but the token exists
		//TODO Instead of saving the user as it is, the Id of the user should be saved here. The user should be fetched using the Id
		if(user == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		if(verificationTokenEntity.getExpiry().getTime() - calendar.getTime().getTime() <= 0) {
			//TODO Provide option for regenerating verification token
			verificationTokenRepository.delete(verificationTokenEntity);
			return null;
		}
//		StatusEnum userStatusEnum = user.getStatus();
		StatusEnum userStatusEnum = user.getEnableStatus();
		if(userStatusEnum == StatusEnum.DISABLED || userStatusEnum == StatusEnum.USER_DISABLED) {
//			user.setStatus(userStatusEnum.ENABLED);
			user.setEnableStatus(StatusEnum.ENABLED);
			System.out.println("Reached Here !!!! Verification");
			userAuthRepository.save(user);
			verificationTokenRepository.delete(verificationTokenEntity);
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	public UserAuthEntity saveToUserCollection(UserAuthEntity user) throws InterruptedException, IOException {
		
		Gson gson = new Gson();
		
		if(user.getRole() == UserRoleEnum.CUSTOMER) {
			//TODO Make call to customer microservice to store the details of user
			
			HttpClient httpClient = HttpClient.newHttpClient();
			URI uri = URI.create("http://localhost:8091/user/customer");
			String userJsonString = gson.toJson(user);
			System.out.println("UserAuthEntity String: " + 
//								user.toString());
								userJsonString);
			HttpRequest userRequest = HttpRequest.newBuilder()
										.setHeader("Content-Type", "application/json")
										.uri(uri)
										.POST(BodyPublishers.ofString(
//												user.toString()))
												userJsonString))
										.build();
			HttpResponse<String> response = httpClient.send(userRequest, 
											HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Response from Customer Service: " + response.body());

			/*
			  	if(customer == null) {
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setFullName(user.getFullName());
				customerEntity.setEmail(user.getEmail());
				customerEntity.setRole(user.getRole());
	
				customerRepository.save(customerEntity);
			}
			else {
				customer.setEnableStatus(EnableStatusEnum.ENABLED);
				customerRepository.save(customer);
			}
			 */
			//TODO Check response of gRPC and decide whether saving was successful
		}
		else if(user.getRole() == UserRoleEnum.SHOP) {
			//TODO Make call to shop microservice to store the details of the user
			/*
			 	ShopEntity shop = shopRepository.findByEmail(user.getEmail());
			if(shop == null) {
				ShopEntity shopEntity = new ShopEntity();
				shopEntity.setShopName(user.getFullName());
				shopEntity.setEmail(user.getEmail());
				shopEntity.setRole(user.getRole());

				shopRepository.save(shopEntity);
			}
			else {
				shop.setEnableStatus(EnableStatusEnum.ENABLED);
				shopRepository.save(shop);
			}
			 */
			//TODO Check response of gRPC and decide whether saving was successful
		}
		return null;
	}

}
