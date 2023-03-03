/**
 * 
 */
package com.pigihi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pigihi.service.interfaces.GenerateTokenServiceInterface;

/**
 * Implementation class of {@link GenerateTokenServiceInterface}
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class GenerateTokenServiceImpl implements GenerateTokenServiceInterface {

	/**
	 * Generate token for verifying email
	 * 
	 * @return Returns token as string
	 * 
	 * @author Ashish Sam T George
	 */
	@Override
	public String getEmailToken() {
		String token = UUID.randomUUID().toString();
		return token;
	}

	/**
	 * Generate token for verifying mobile
	 * 
	 * @return Returns token as string
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public String getMobileToken() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate URL containing token for verifying email
	 * 
	 * @param baseUrl Base URL as string
	 * @param emailToken Email token as string
	 * @return Returns URL as string
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public String getEmailTokenUrl(String baseUrl, String emailToken) {
		String emailUrl = baseUrl + "/register/verify?emailToken=" + emailToken;
		return emailUrl;
	}

	/**
	 * Generate URL containing token for verifying mobile
	 * 
	 * @param baseUrl Base URL as string
	 * @param mobileToken Mobile token as string
	 * @return Returns URL as string
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public String getMobileTokenUrl(String baseUrl, String mobileToken) {
		String mobileUrl = baseUrl + "/register/verify?mobileToken=" + mobileToken;
		return mobileUrl;
	}

}
