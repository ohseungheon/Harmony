package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class ApiLoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/doLogin")
	public String login(@RequestBody UserDto userDto,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		UserDto user = loginService.loginCheckService(userDto);
		
		if(user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
		}
		
		return "success";
	}
	
}
