package cdb.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CdbWebSecurityConfig {

	private final CdbAuthSuccessHandler cdbAuthSuccessHandler;

	private final CdbAuthFailureHandler cdbAuthFailureHandler;

	@Bean
	SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> authorize	.antMatchers("/actuator", "/actuator/**")
																									.denyAll()
																									.antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**")
																									.permitAll()
																									.anyRequest()
																									.authenticated())
				.formLogin(formLogin -> formLogin	.loginPage("/sign/signin")
																					.loginProcessingUrl("/sign/signin")
																					.usernameParameter("email")
																					.passwordParameter("password")
																					.successHandler(cdbAuthSuccessHandler)
																					.failureHandler(cdbAuthFailureHandler)
																					.permitAll())
		// .csrf(CsrfConfigurer<HttpSecurity>::disable)
		;
		http.logout() // 로그아웃 처리
				.logoutUrl("/signout") // 로그아웃 처리 URL
				.logoutSuccessUrl("/sign/signin") // 로그아웃 성공 후 이동페이지
				.invalidateHttpSession(true);// 세션 전체삭제 여부
		return http.build();
	}

}
