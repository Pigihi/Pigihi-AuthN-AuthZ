/**
 * 
 */
package com.pigihi.utility;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.pigihi.entity.UserAuthEntity;
import com.pigihi.model.UserRoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


/**
 * Utility class for JWT operations
 * 
 * @author Ashish Sam T George
 *
 */
@Component
public class JWTUtility implements Serializable {
	
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private static String secretKey = "01100110011110010100011010001001011101001000000011100001110001111000011101011011001011010101101111100000111011000100001010100110010001101100010110101010111001101110010110111000001100010001010101001010010011011110101111110111101110001011000100110011011101010111001110001100000101100110111111111101111001100000000111111100000010101100001001010111010011110110111100010010000111010000000100110111111110011111110100110010010001000000110101001010001100101100001001011011110110101011000101011100111010111111001000101111";
	
	private final Key signatureSecretKey = generateSecretKey();
	
	public String generateToken(UserAuthEntity user) {
		Map<String, Object> claims = new HashMap<>();
		
		return doGenerateToken(user, user.getEmail());
	}
	
	private Key generateSecretKey() {
		SecretKey signatureSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		return signatureSecretKey;
	}
	
	private String doGenerateToken(UserAuthEntity user, String subject) {
		Map<String, Object> claimsMap = new HashMap<>();
		
		if(user.getRole() == UserRoleEnum.SHOP){
//			ShopEntity shop = shopService.shopInfo(subject);
			//TODO Make API call to shop microservice for getting id of the shop
//			claimsMap.put("id", shop.getId());
		}
		else if(user.getRole() == UserRoleEnum.CUSTOMER) {
//			CustomerEntity customer = customerService.customerInfo(subject);
			//TODO Make API call to customer microservice for getting id of the customer
//			claimsMap.put("id", customer.getId());
		}
		
		claimsMap.put("authorities", user.getAuthorities(List.of(user.getRole()), user.getPrivileges()));
		Claims claims = Jwts.claims(claimsMap);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(signatureSecretKey).compact();
	}
	
	public Claims getBody(String token) {
		return Jwts.parserBuilder().setSigningKey(signatureSecretKey).build().parseClaimsJws(token).getBody();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(signatureSecretKey).build().parseClaimsJws(token).getBody();
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Boolean validateToken(String token) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(user.getEmail()) && !isTokenExpired(token));
		return (!isTokenExpired(token));

	}

}
