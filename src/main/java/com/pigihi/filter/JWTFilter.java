/**
 * 
 */
package com.pigihi.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.utility.JWTUtility;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class for handling JWT
 * 
 * @author Ashish Sam T George
 *
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtility jwtUtility;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Check whether the user is enabled or not. If the user account has been
				// disabled, then the user
				// should not be able to do any operations until enabled again.
		
		//TODO Where is the actual JWT validation taking place?
		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			token = authorizationHeader.substring(7);
			username = jwtUtility.getUsernameFromToken(token);
		}
		else {
			throw new ServletException("Authorization header not provided");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if(jwtUtility.validateToken(token)) {
				Claims body = jwtUtility.getBody(token);
				List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
				Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
						authorities.stream()
						.map(m -> new SimpleGrantedAuthority(m.get("authority")))
						.collect(Collectors.toSet());
				
				Authentication usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			else {
				throw new ServletException("Cannot validate token");
			}
			filterChain.doFilter(request, response);
		}
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		Collection<String> excludeUrlPatterns = new ArrayList<>();
		excludeUrlPatterns.add("/auth/login/user");
		excludeUrlPatterns.add("/auth/register/user");
		excludeUrlPatterns.add("/auth/verify/user/**");
		excludeUrlPatterns.add("/auth/authorize/user");
		
		AntPathMatcher pathMatcher = new AntPathMatcher();
		return excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
		
	}
	
	

}
