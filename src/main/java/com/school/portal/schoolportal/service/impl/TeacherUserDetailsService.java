package com.school.portal.schoolportal.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.school.portal.schoolportal.model.Teacher;
import com.school.portal.schoolportal.model.UserPrinciple;
import com.school.portal.schoolportal.repo.TeacherRepository;

@Component("teacherDetails")
public class TeacherUserDetailsService implements UserDetailsService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Teacher teacher = teacherRepository.findByUserName(userName);

		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_ADMIN");

		return UserPrinciple.build(new User(teacher.getUserName(), teacher.getPassword(), Arrays.asList(auth)));
	}

}
