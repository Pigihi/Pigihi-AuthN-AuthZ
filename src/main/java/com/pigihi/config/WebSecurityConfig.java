/**
 * 
 */
package com.pigihi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pigihi.filter.JWTFilter;

/**
 * Configuration class for web security
 * 
 * @author Ashish Sam T George
 *
 */
@EnableWebSecurity
@Configuration
// It is working when '@Configuration' is used and not when using '@EnableWebSecurity'
public class WebSecurityConfig {
	
	//TODO Put a limitation for the number of users that can be added or deleted or so on, in a single request.
//	Also, include pagination for responses.
	
	@Autowired
	private JWTFilter jwtFilter;
	
	private static final String[] WHITE_LIST_URLS = {
			"/auth/login/user", "/auth/register/user", "/ashish/soman"
	};
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(WHITE_LIST_URLS)
			.permitAll()
			// In the new spring security, antMatchers is deprecated
			// Additional antMatchers
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		return http.build();
	}

}
