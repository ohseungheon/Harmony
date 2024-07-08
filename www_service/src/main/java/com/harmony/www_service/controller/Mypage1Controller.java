package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dto.MemberDto;


@Controller
public class Mypage1Controller {
    @Autowired
    IMemberDao memDao;
	//로그인한 회원번호로 회원정보 조회 
	@RequestMapping("/info_update")
	public String infoUpdate(@RequestParam("mno") int mno, Model model) {
		MemberDto member = memDao.getmemberList(mno);
		model.addAttribute("member", member);
		//System.out.println(member);
		return "mypage1/info_update";
	}
	
}
