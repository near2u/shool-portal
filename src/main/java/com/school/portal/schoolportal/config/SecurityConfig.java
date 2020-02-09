package com.school.portal.schoolportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.school.portal.schoolportal.jwttoken.auth.JwtAuthTokenFilter;
import com.school.portal.schoolportal.jwttoken.auth.JwtAuthTokenFilterStudent;
import com.school.portal.schoolportal.service.impl.StudentDetailsService;
import com.school.portal.schoolportal.service.impl.TeacherUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Configuration
	@Order(2)
	public static class StudentSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		@Qualifier("studentDetails")
		private StudentDetailsService studentDetailsService;
		
		@Autowired
		private JwtAuthTokenFilterStudent jwtTokenFilterStudent;
		
		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().authorizeRequests().antMatchers("/students/{studentId}").hasRole("USER").

					and().authorizeRequests().antMatchers("/signin/**").permitAll().and().httpBasic()
					.realmName("School security application Realm")
					.authenticationEntryPoint(new SchoolPortalEntryPoint()).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			http.addFilterBefore(jwtTokenFilterStudent, UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		// @Qualifier("studentAuth")
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(studentDetailsService).passwordEncoder(passwordEncoder);

		}

		/*
		 * @Bean("studentAuth")
		 * 
		 * @Override public AuthenticationManager authenticationManagerBean()
		 * throws Exception { return super.authenticationManagerBean(); }
		 */
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

	}

	@Configuration
	@Order(1)
	public static class TeacherSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private JwtAuthTokenFilter jwtAuthTokenFilter;

		@Autowired
		@Qualifier("teacherDetails")
		private TeacherUserDetailsService teacherUserDetailsService;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().authorizeRequests().antMatchers("/teachers/**").hasRole("ADMIN").and()
					.authorizeRequests().antMatchers("/signin/**").permitAll().and().httpBasic()
					.realmName("School security application Realm")
					.authenticationEntryPoint(new SchoolPortalEntryPoint()).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		// @Qualifier("teacherAuth")
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(teacherUserDetailsService).passwordEncoder(passwordEncoder);
		}

		/*@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}*/

	}

	/*@Bean
	public AuthenticationProvider authProvider() {
		return new AuthenticationProvider() {

			@Override
			public boolean supports(Class<?> authentication) {
				return authentication.equals(UsernamePasswordAuthenticationToken.class);
			}

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {

				String userName = (String) authentication.getPrincipal();
				String password = (String) authentication.getCredentials();

				return new UsernamePasswordAuthenticationToken(userName, password, authentication.getAuthorities());
			}
		};
	}*/

}
