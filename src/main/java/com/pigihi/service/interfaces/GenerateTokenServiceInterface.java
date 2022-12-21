/**
 * 
 */
package com.pigihi.service.interfaces;

/**
 * @author Ashish Sam T George
 *
 */
public interface GenerateTokenServiceInterface {

	String getEmailToken();

	String getMobileToken();

	String getEmailTokenUrl(String emailToken, String string);

	String getMobileTokenUrl(String baseUrl, String mobileToken);

}
