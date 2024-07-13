package com.harmony.www_service.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;
import com.harmony.www_service.service.LoginService;
import com.harmony.www_service.service.MyRecipeService;
import com.harmony.www_service.util.FileUploadUtil;

@Controller
@RequestMapping("/my/recipe")
public class Mypage2Controller {
	
	@Autowired
	private MyRecipeService service;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RecipeMenuDao menuDao;
	
	@RequestMapping("/list")
	public String myRecipeList() {
		
		return "mypage2/my_recipe_list";
	}
	
	@RequestMapping("/registForm")
	public String myRecipeRegist(Model model) {
	
		List<MenuDto> list = menuDao.getMenu();
		
		model.addAttribute("list", list);
		
		return "mypage2/recipe_regist";
	}
	
	@PostMapping("/regist")
	@Transactional
	public String recipeRegist(@RequestParam("mcode") int mcode,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("introduce") String introduce,
			@RequestParam("url") String url,
			@RequestParam("category") String category,
			@RequestParam("portions") int portions,
			@RequestParam("icode") int icode,
			@RequestParam("amount") int amount,
			@RequestParam("type") String type,
			@RequestParam("orderNum") int orderNum,
			@RequestParam("orderContent") String orderContent,
			@RequestPart("cookingImg") MultipartFile cookingImg,
			@RequestParam("tagContent") String tagContent)
			{
		
		String cookingImgName = "";
		

		try {
			if (cookingImg != null && !cookingImg.isEmpty()) {
				cookingImgName = FileUploadUtil.saveFile(cookingImg.getOriginalFilename(), cookingImg);
	        }

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto mno = loginService.getMnoByUsernameService(username);
		System.out.println("mno = " + mno.getMno());
		
		// recipe 테이블 등록
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setMcode(mcode);
		recipeDto.setMno(mno.getMno());
		recipeDto.setRecipeName(recipeName);
		recipeDto.setIntroduce(introduce);
		recipeDto.setUrl(url);
		recipeDto.setCategory(category);
		recipeDto.setPortions(portions);
		service.registMyRecipeService(recipeDto);
		
		//int rcode = recipeDto.getRcode();
		//System.out.println("rcode = " + rcode);
		
		// 레시피 재료 등록
		RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
		recipeIngredientDto.setRcode(recipeDto.getRcode());
		recipeIngredientDto.setIcode(icode);
		recipeIngredientDto.setAmount(amount);
		service.registMyRecipeIngredientService(recipeIngredientDto);
		
		// 요리순서 등록
		RecipeOrderDto recipeOrderDto = new RecipeOrderDto();
		recipeOrderDto.setRcode(recipeDto.getRcode());
		recipeOrderDto.setOrderContent(orderContent);
		recipeOrderDto.setOrderNum(orderNum);
		recipeOrderDto.setCookingImg(cookingImgName);
		service.registMyRecipeOrderService(recipeOrderDto);
		
		// 태그 등록
		RecipeTagDto recipeTagDto = new RecipeTagDto();
		recipeTagDto.setRcode(recipeDto.getRcode());
		recipeTagDto.setTagContent(tagContent);
		service.registMyRecipeTagService(recipeTagDto);
		
		
		return "redirect:list?mno=" + mno.getMno();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
