package com.harmony.www_service.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dao.IIngredientDao;
import com.harmony.www_service.dao.ILikeDao;
import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dao.IMypage1Dao;
import com.harmony.www_service.dto.FridgeIngredientDto;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MemberDto_by;
import com.harmony.www_service.dto.Menu_favoriteDto_by;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.Recipe_recommendDto_by;
import com.harmony.www_service.service.LikeService;
import com.harmony.www_service.service.MemberService;

@Controller
@RequestMapping("/my")
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
	@Autowired
	IIngredientDao ingreDao;

	// 회원정보수정페이지 - 로그인한 회원번호로 회원정보 조회
	@RequestMapping("/info_update")
	public String infoUpdate(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println("아이디---------------" + username);
		model.addAttribute("username", username);

		MemberDto_by member = memDao.getmemberList(username);
		model.addAttribute("member", member);
		//System.out.println("멤버=============" + member);
		return "mypage1/info_update";
	}

	// 좋아요페이지
	@RequestMapping("/like_hate_list")
	public String like_hate_list(Model model) {
		// 로그인한 회원번호로 좋아요 출력
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println("아이디===============" + username);
		MemberDto_by member = memberService.getMemberByUsername(username);
		int mno = member.getMno();
		//System.out.println("mno>>>>>>" + mno);
		model.addAttribute("mno", mno);
		// 추천한 레시피 리스트
		List<Recipe_recommendDto_by> recipeLike = likeService.getRecipeLike(mno);
		//System.out.println("레시피좋아요!!!!!!!!!!!!!!" + recipeLike);
		model.addAttribute("recipeLike", recipeLike);
		// 좋아요 메뉴 리스트
		List<Menu_favoriteDto_by> menuLike = likeService.getMenuLike(mno);
		//System.out.println("메뉴좋아요!!!!!!!!" + menuLike);
		model.addAttribute("menuLike", menuLike);

		return "mypage1/like_hate_list";
	}

	// 마이페이지 메인
	@RequestMapping("/mypage_main")
	public String mypageMain(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println("아이디===============" + username);
		MemberDto_by member = memberService.getMemberByUsername(username);
		int mno = member.getMno();
		model.addAttribute("mno", mno);
		
		// 마이프로필
		model.addAttribute("member", member);
		
		//메인 - 좋아요 3개만 나오게
		List<Recipe_recommendDto_by> recipeLike = likeDao.getMainRecipeLike(mno);
		model.addAttribute("recipeLike", recipeLike);
		//메인 - 내가 등록한 레시피 3개만 나오게
		List<RecipeDto> myRecipe = likeDao.getMyRecipeList(mno);
		model.addAttribute("my", myRecipe);
		
		// all냉동
		List<FridgeIngredientDto> ice = myDao.getAllIceList(mno);
		//System.out.println("냉동all====" + ice);
		model.addAttribute("ice", ice);
		// all냉장
		List<FridgeIngredientDto> cool = myDao.getAllCoolList(mno);
		//System.out.println("냉장all+++" + cool);
		model.addAttribute("cool", cool);
		// all상온
		List<FridgeIngredientDto> food = myDao.getAllFoodList(mno);
		//System.out.println("상온all>>>>" + food);
		model.addAttribute("food", food);
		
		// 디데이
		//내 재료 all 중 유통기한 임박 10개만 
		List<FridgeIngredientDto> dd = myDao.getAllDayList(mno);
		List<Long> days = new ArrayList<>();
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		
		for(FridgeIngredientDto i : dd) {
			long dday = ChronoUnit.DAYS.between(i.getDeadline(), now);
			days.add(dday);
		}
		model.addAttribute("dd", dd);
		model.addAttribute("days", days);
		
		//s
		List<Long> daysBetween_ice = new ArrayList<>();
		List<Long> daysBetween_cool = new ArrayList<>();
		List<Long> daysBetween_food = new ArrayList<>();
		for (int i = 0; i < ice.size(); i++) {
			daysBetween_ice.add(ChronoUnit.DAYS.between(ice.get(i).getDeadline(), now));
		}
		for (int i = 0; i < cool.size(); i++) {
			daysBetween_cool.add(ChronoUnit.DAYS.between(cool.get(i).getDeadline(), now));
		}
		for (int i = 0; i < food.size(); i++) {
			daysBetween_food.add(ChronoUnit.DAYS.between(food.get(i).getDeadline(), now));
		}

		model.addAttribute("dayIce", daysBetween_ice);
		model.addAttribute("dayCool", daysBetween_cool);
		model.addAttribute("dayFood", daysBetween_food);

		return "mypage1/mypage_main";
	}

	// 재료상세페이지
	@RequestMapping("/material_page")
	public String goMaterial(Model model) {

		return "mypage1/material_page";
	}

	// 재료수정페이지
	@RequestMapping("/material_update")
	public String goMaterialUpdate(@RequestParam("fcode") int fcode, Model model) {
		FridgeIngredientDto fi = myDao.getMaterialList(fcode);
		//System.out.println("수정할재료정보리스트" + fi);
		model.addAttribute("material", fi);
		
		List<String> keeptypes = Arrays.asList("냉장", "냉동", "상온"); // 옵션데이터
		model.addAttribute("keeptypes", keeptypes);
		return "mypage1/mypage_main";
	}

	// 재료등록페이지
	@RequestMapping("/material_insert")
	public String goMaterialInsert(Model model, @RequestParam("mno") int mno) {
		
		List<String> keeptypes = Arrays.asList("냉장", "냉동", "상온"); // 옵션데이터
		model.addAttribute("keeptypes", keeptypes);
		
		model.addAttribute("mno", mno);
		//재료 카테고리 위한
		List<IngredientDto> ingredientCt = ingreDao.findByCategory(null);
		List<IngredientDto> ingredientDto = myDao.allIngredientList();
		model.addAttribute("ingre", ingredientDto);
		//System.out.println("재료싹다"+ingredientDto);
		return "mypage1/material_insert";
	}

}
