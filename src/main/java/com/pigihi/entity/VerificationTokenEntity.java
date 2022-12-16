/**
 * 
 */
package com.pigihi.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Verification token entity class
 * 
 * @author Ashish Sam T George
 *
 */
@Data
@NoArgsConstructor
@Document(collection = "verification_token_collection")
public class VerificationTokenEntity {
	
	@Id
	private String id;
	private String emailToken;
	private String mobileToken;
	private Date expiry;
	
	private UserAuthEntity user;
	
	private static final int EXPIRATION_TIME = 10;
	
	public VerificationTokenEntity(UserAuthEntity user, String emailToken, String mobileToken) {
		super();
		this.user = user;
		this.emailToken = emailToken;
		this.mobileToken = mobileToken;
		this.expiry = calculateExpirationTime(EXPIRATION_TIME);
	}
	
	/**
	 * @param expirationTime Number of minutes within which the token will expire, expressed in integer
	 * @return Returns Date at which the token will expire
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	private Date calculateExpirationTime(int expirationTime) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
		
	}

}
