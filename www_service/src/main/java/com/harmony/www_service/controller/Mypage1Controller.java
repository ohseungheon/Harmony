package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dto.MemberDto_by;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class Mypage1Controller {
    @Autowired
    IMemberDao memDao;
    
	//로그인한 회원번호로 회원정보 조회 
	@RequestMapping("/info_update")
	public String infoUpdate(Model model, HttpServletRequest req) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);
		
		MemberDto_by member = memDao.getmemberList(username);
		model.addAttribute("member", member);
		System.out.println("멤버============="+member);
		return "mypage1/info_update";
	}
	
	@RequestMapping("/mypage_main")
	public String mypageMain() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("아이디==============="+username);
		return "mypage1/mypage_main";
	}
	
	@RequestMapping("/like_hate_list")
	public String like_hate_list() {
		return "mypage1/like_hate_list";
	}
	
}

