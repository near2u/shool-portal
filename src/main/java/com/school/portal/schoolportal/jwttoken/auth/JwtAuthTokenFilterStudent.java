package com.school.portal.schoolportal.jwttoken.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.portal.schoolportal.service.impl.StudentDetailsService;

@Component
public class JwtAuthTokenFilterStudent extends OncePerRequestFilter {

	@Autowired
	private StudentDetailsService studentDetailsService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = getToken(request);

			if (token != null && jwtTokenProvider.validateToken(token)) {

				String userName = jwtTokenProvider.getUserNameFromJwtToken(token);

				UserDetails userDetails = studentDetailsService.loadUserByUsername(userName);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else {

			}
		} catch (Exception e) {
			System.out.println("validation Failed");
		}

		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			return token.replace("Bearer ", "");
		}

		return null;
	}

}
