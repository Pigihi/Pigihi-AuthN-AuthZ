/**
 * 
 */
package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for verification tokens that are to be pushed to the message broker
 * 
 * @author Ashish Sam T George
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenQueueModel {
	
	private String emailSubject;
	private String emailBody;
	private String emailSendTo;
	
	private String mobileSubject;
	private String mobileBody;
	private String mobileSendTo;
	
}
