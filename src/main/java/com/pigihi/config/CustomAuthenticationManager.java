/**
 * 
 */
package com.pigihi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.service.UserService;
import com.pigihi.service.interfaces.UserLoginServiceInterface;

/**
 * Configuration class for authentication manager
 * 
 * @author Ashish Sam T George
 *
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		String email = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
		UserAuthEntity user = userService.findByEmail(email);
		String password = user.getPassword();
		if(user != null) {
			if(passwordEncoder.matches(rawPassword, password)) {
				return new UsernamePasswordAuthenticationToken(user, 
						null,
						user.getAuthorities(List.of(user.getRole())));
			}
		}
		else {
			throw new BadCredentialsException("Credentials are wrong");
			//TODO Debug this
		}
		return null;
	}

}
