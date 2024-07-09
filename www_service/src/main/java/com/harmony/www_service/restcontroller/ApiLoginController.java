package com.harmony.www_service.restcontroller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.dto.UserMemberDto;
import com.harmony.www_service.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class ApiLoginController {
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	private LoginService loginService;

	// 로그인
	@PostMapping("/doLogin")
	public String login(@RequestBody UserDto userDto,
			Model model) {
		
		UserDto user = loginService.loginCheckService(userDto);
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		
		return "success";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "logout success";
	}
	
	// 회원가입
	@PostMapping("/doRegist")
	public String regist(@RequestBody UserMemberDto userMemberDto) {
		
		System.out.println(userMemberDto);
		
		userMemberDto.setRole("ROLE_USER");
		userMemberDto.setPassword(pwEncoder.encode(userMemberDto.getPassword()));
		
		int resultUser = loginService.registUserService(userMemberDto);
		int resultMember = loginService.registMemberService(userMemberDto);
		
		if(resultUser == 1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
}
