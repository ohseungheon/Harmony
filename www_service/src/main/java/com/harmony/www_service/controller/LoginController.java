package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login_page")
	public String loginForm(@RequestParam(name = "error", required = false) Integer error, Model model) {
		if (error != null && error == 1) {
			model.addAttribute("err_msg", "아이디 또는 비밀번호가 틀렸습니다.");
			return "main/login_page";
		}

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
	
}
