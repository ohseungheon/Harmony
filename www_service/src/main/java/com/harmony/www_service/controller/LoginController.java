package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login_page")
	public String loginForm(@RequestParam(name = "error", required = false) Integer error, Model model, HttpServletRequest request, HttpSession session) {
		if (error != null && error == 1) {
			model.addAttribute("err_msg", "아이디 또는 비밀번호가 틀렸습니다.");
			return "main/login_page";
		}
		// 로그인 성공 시 로그인 페이지로 이동 전의 페이지로 redirect 해주기 위한 이전 페이지 url 저장
		String referer = request.getHeader("Referer");
        if (referer != null && !referer.contains("/login_page")) {
            session.setAttribute("prevPage", referer);
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
