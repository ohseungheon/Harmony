package com.harmony.www_service.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response) {
		//getRedirectStrategy().sendRedirect(request, response, "/login_page?error");
		
	}

}
