package cdb.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CdbSecurityConfigBean {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	RoleHierarchy roleHierarchy() {
		final var hierarchy = new RoleHierarchyImpl();
		hierarchy.setHierarchy("ROLE_HR > ROLE_TEACHER > ROLE_STAFF > ROLE_TEMP");
		return hierarchy;
	}

}
