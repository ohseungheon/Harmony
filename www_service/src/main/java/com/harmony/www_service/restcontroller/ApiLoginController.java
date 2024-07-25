package com.harmony.www_service.restcontroller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.LoginDao;
import com.harmony.www_service.dto.UserMemberDto;
import com.harmony.www_service.service.LoginService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class ApiLoginController {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private LoginService loginService;


	// 회원가입
	@PostMapping("/doRegist")
	public ResponseEntity<?> regist(@RequestBody UserMemberDto userMemberDto) {
	    try {
	        // 아이디 중복 검사
	        if (loginService.isUsernameDuplicate(userMemberDto.getUsername())) {
	            return ResponseEntity.badRequest().body("Username already exists");
	        }

	        // 회원가입 처리
	        int resultUser = loginService.registUserService(userMemberDto);
	        int resultMember = loginService.registMemberService(userMemberDto);

	        if (resultUser == 1 && resultMember == 1) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.internalServerError().body("fail");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("An error occurred during registration");
	    }
	}
	
//	회원가입시 아이디 중복검사
	@GetMapping("/checkUsername")
	public ResponseEntity<?> checkUsername(@RequestParam("username") String username){
		try {
			boolean isDuplicate = loginService.isUsernameDuplicate(username);
			return ResponseEntity.ok().body(new HashMap<String, Boolean>(){{
				put("isDuplicate", isDuplicate);
			}});
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body("Error checking username");
		}
	}
	
//	로그인한 유저 닉네임 가져오기
	@GetMapping("/nickname")
	public String nickname() {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		String nickname = loginDao.getNickname(username);
		
		return nickname;
	}
	
	
	
	
	
	
	
	
	
	
	

}
