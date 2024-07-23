package com.harmony.www_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/login_page", "/regist_page", "/doLogin", "/doRegist", "/loginProc").permitAll()
						.requestMatchers("/src/**", "/main/**","/resources/**", "/static/**", "/img/**", "/json/**").permitAll()
						.requestMatchers("/uploads/**").permitAll()
						.requestMatchers("/api/**").permitAll()
						.requestMatchers("/menu_all/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
						// .anyRequest().authenticated()
						.anyRequest().permitAll()
						);
		http
				.formLogin((auth) -> auth
				.loginPage("/login_page")
				.loginProcessingUrl("/loginProc")
				// .defaultSuccessUrl("/", true)	// 로그인 성공 시 무조건 메인으로 가는 코드
				.successHandler(successHandler) // 커스텀 핸들러 설정
				.failureHandler(customAuthenticationFailureHandler)
				.permitAll()
				);
		
		http
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
						);
		
		http
				.csrf((csrf) -> csrf.disable());
						
				
		
		return http.build();

	}

}
