package com.harmony.www_service.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class ApiLoginController {

	

	@Autowired
	private LoginService loginService;

	// 로그인
//	@PostMapping("/doLogin")
//	public ResponseEntity<Map<String, Object>>login(@RequestBody UserDto userDto) {
//		
//		UserDto user = loginService.loginCheckService(userDto);
//		System.out.println(user);
//		
//		if(user != null) {
//			Map<String, Object> responseData = new HashMap<>();
//			responseData.put("username", user.getUsername());
//			responseData.put("role", user.getRole());
//			
//			System.out.println(responseData);
//			
//			return ResponseEntity.ok(responseData);
//		}else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//		
//	}


	

	// 회원가입
	@PostMapping("/doRegist")
	public String regist(@RequestBody UserMemberDto userMemberDto) {

		System.out.println(userMemberDto);


		int resultUser = loginService.registUserService(userMemberDto);
		int resultMember = loginService.registMemberService(userMemberDto);

		if (resultUser == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

}
