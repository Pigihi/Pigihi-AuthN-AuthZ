/**
 * 
 */
package com.pigihi.service;

import com.pigihi.service.interfaces.GenerateTokenServiceInterface;

/**
 * @author Ashish Sam T George
 *
 */
public class GenerateTokenServiceImpl implements GenerateTokenServiceInterface {

	@Override
	public String getEmailToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMobileToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailTokenUrl(String baseUrl, String emailToken) {
		String emailUrl = baseUrl + "/register/verifyEmail?token=" + emailToken;
		return emailUrl;
	}

	@Override
	public String getMobileTokenUrl(String baseUrl, String mobileToken) {
		String mobileUrl = baseUrl + "/register/verifyMobile?token=" + mobileToken;
		return mobileUrl;
	}

}
