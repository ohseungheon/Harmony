package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login_page")
	public String loginForm() {
		
		return "main/login_page";
	}
	
	@RequestMapping("/regist_page")
	public String registForm() {
		
		return "main/regist_page";
	}

}
