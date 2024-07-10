package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.ILikeDao;
import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dao.IMypage1Dao;
import com.harmony.www_service.dto.FridgeIngredientDto;
import com.harmony.www_service.dto.MemberDto_by;
import com.harmony.www_service.dto.Menu_favoriteDto_by;
import com.harmony.www_service.dto.Recipe_recommendDto_by;
import com.harmony.www_service.service.LikeService;
import com.harmony.www_service.service.MemberService;


@Controller
public class Mypage1Controller {
    @Autowired
    IMemberDao memDao;
    @Autowired
    ILikeDao likeDao;
    @Autowired
    IMypage1Dao myDao;
    @Autowired
    MemberService memberService;
    @Autowired
    LikeService likeService;
    
	//회원정보수정페이지 - 로그인한 회원번호로 회원정보 조회 
	@RequestMapping("/info_update")
	public String infoUpdate(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("아이디---------------"+username);
		model.addAttribute("username", username);
		
		MemberDto_by member = memDao.getmemberList(username);
		model.addAttribute("member", member);
		System.out.println("멤버============="+member);
		return "mypage1/info_update";
	}
	
	//좋아요페이지 
	@RequestMapping("/like_hate_list")
	public String like_hate_list(Model model) {
		//로그인한 회원번호로 좋아요 출력
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("아이디==============="+username);
		MemberDto_by member = memberService.getMemberByUsername(username);
		int mno = member.getMno();
		System.out.println("mno>>>>>>"+mno);
		model.addAttribute("mno", mno);
		//추천한 레시피 리스트
		List<Recipe_recommendDto_by> recipeLike = likeService.getRecipeLike(mno);
		System.out.println("레시피좋아요!!!!!!!!!!!!!!"+recipeLike);
		model.addAttribute("recipeLike", recipeLike);
		//좋아요 메뉴 리스트
		List<Menu_favoriteDto_by> menuLike = likeService.getMenuLike(mno);
		System.out.println("메뉴좋아요!!!!!!!!"+menuLike);
		model.addAttribute("menuLike", menuLike);
		
		return "mypage1/like_hate_list";
	}
	
	//마이페이지 메인 
	@RequestMapping("/mypage_main")
	public String mypageMain(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("아이디==============="+username);
		MemberDto_by member = memberService.getMemberByUsername(username);
		int mno = member.getMno();
		model.addAttribute("mno", mno);
		
		//냉동
		List<FridgeIngredientDto> ice = myDao.getIceList(mno);
		System.out.println("냉동===="+ice);
		model.addAttribute("ice", ice);
		//냉장
		List<FridgeIngredientDto> cool = myDao.getCoolList(mno);
		System.out.println("냉장+++"+cool);
		model.addAttribute("cool", cool);
		//상온
		List<FridgeIngredientDto> food = myDao.getFoodList(mno);
		System.out.println("상온>>>>"+food);
		model.addAttribute("food", food);
		
		return "mypage1/mypage_main";
	}
	
	
}

