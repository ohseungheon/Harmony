package com.harmony.www_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/login_page", "/regist_page").permitAll()
				.requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**").permitAll()
				//.requestMatchers("/admin").hasRole("ADMIN")
				//.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().permitAll()
				);
		
		http.formLogin(form -> form.disable());
		
		return http.build();
			
	}

}
