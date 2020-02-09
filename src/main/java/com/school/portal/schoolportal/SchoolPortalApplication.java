package com.school.portal.schoolportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication

public class SchoolPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolPortalApplication.class, args);
	}

}
