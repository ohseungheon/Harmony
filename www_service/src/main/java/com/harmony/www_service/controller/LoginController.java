package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("/login_page")
	public String loginForm() {

		return "main/login_page";
	}

	@RequestMapping("/regist_page")
	public String registForm() {

		return "main/regist_page";
	}
	
	@RequestMapping("/loginProc")
	public String loginProc(UserDto userDto) {
		
		UserDto user = loginService.loginCheckService(userDto);
		
		if(user != null) {
			
		}
		
		return "/";
	}


}
