package com.school.portal.schoolportal.jwttoken.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.school.portal.schoolportal.model.UserPrinciple;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${app.jwtSecret}")
	private String jwtSecrete;

	@Value("${app.jwtEpiration}")
	private int jwtExpiration;

	public boolean validateToken(String token) {

		try {
			Jwts.parser().setSigningKey(jwtSecrete).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("Invalid Token");
		}
		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecrete).parseClaimsJws(token).getBody().getSubject();
	}

	public String generateJwtToken(Authentication authentication) {
		UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
		return  Jwts.builder().setSubject(principal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS256, jwtSecrete).compact()
				;
	}
}
