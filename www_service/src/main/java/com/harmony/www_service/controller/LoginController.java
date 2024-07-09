package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.service.LoginService;

import jakarta.servlet.http.HttpSession;

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
		
		
		return "/";
	}
	
	@PostMapping("/clearErrorMessage")
	public ResponseEntity<Void> clearErrorMessage(HttpSession session) {
		
		session.removeAttribute("errormsg");
		
		return ResponseEntity.ok().build();
	}


}
