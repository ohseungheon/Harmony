package com.harmony.www_service.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
	
	// 나의 레시피 리스트
	@RequestMapping("/list")
	public String myRecipeList() {
		
		return "mypage2/my_recipe_list";
	}
	
	// 레시피 등록 폼
	@RequestMapping("/registForm")
	public String myRecipeRegist(Model model) {
	
		List<MenuDto> list = menuDao.getMenu();
		
		model.addAttribute("list", list);
		
		return "mypage2/recipe_regist";
	}
	
	
	// 레시피 등록
	@PostMapping("/regist")
	@Transactional
	public String recipeRegist(@RequestParam("mcode") int mcode,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("introduce") String introduce,
			@RequestParam("url") String url,
			@RequestParam("category") String category,
			@RequestParam("portions") int portions,
			@RequestParam("icode") List<Integer> icode,
			@RequestParam("amount") List<Integer> amount,
			@RequestParam("orderNum") List<Integer> orderNum,
			@RequestParam("orderContent") List<String> orderContent,
			@RequestPart("cookingImg") List<MultipartFile> cookingImg,
			@RequestParam("tagContent") String tagContent)
			{
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto mno = loginService.getMnoByUsernameService(username);
		System.out.println("mno = " + mno.getMno());
		
		// recipe 테이블 등록
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setMcode(mcode);
		recipeDto.setMno(mno.getMno());
		recipeDto.setRecipeName(recipeName);
		recipeDto.setIntroduce(introduce);
		if(url.length() > 12) {
			recipeDto.setUrl(url.substring(url.length() - 11));
		}else {
			recipeDto.setUrl(url);
		}
		recipeDto.setCategory(category);
		recipeDto.setPortions(portions);
		service.registMyRecipeService(recipeDto);
		
		//int rcode = recipeDto.getRcode();
		//System.out.println("rcode = " + rcode);
		
		// 레시피 재료 등록 재료가 두개이상이면 List로 등록
		for(int i = 0; i < icode.size(); i ++) {
			
			RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
			recipeIngredientDto.setRcode(recipeDto.getRcode());
			recipeIngredientDto.setIcode(icode.get(i));
			recipeIngredientDto.setAmount(amount.get(i));
			service.registMyRecipeIngredientService(recipeIngredientDto);
			
		}
		
		// 요리순서 등록 순서가 두개 이상이면 List로 등록
		for(int i = 0; i < orderNum.size(); i++) {
			
			RecipeOrderDto recipeOrderDto = new RecipeOrderDto();
			recipeOrderDto.setRcode(recipeDto.getRcode());
			recipeOrderDto.setOrderContent(orderContent.get(i));
			recipeOrderDto.setOrderNum(orderNum.get(i));
			
			if(!cookingImg.get(i).isEmpty()) {
				try {
					String cookimgImgName = FileUploadUtil.saveFile(cookingImg.get(i).getOriginalFilename(), cookingImg.get(i));
					recipeOrderDto.setCookingImg(cookimgImgName);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			service.registMyRecipeOrderService(recipeOrderDto);
			
		}
		
		// 태그 등록
		RecipeTagDto recipeTagDto = new RecipeTagDto();
		recipeTagDto.setRcode(recipeDto.getRcode());
		recipeTagDto.setTagContent(tagContent);
		service.registMyRecipeTagService(recipeTagDto);
		
		
		return "redirect:list?mno=" + mno.getMno();
	}
	
//	레시피 수정 폼
	@GetMapping("/updateForm")
	@Transactional
	public String updateForm(Model model,
			@RequestParam("rcode") int rcode) {
		
		List<MenuDto> menuList = menuDao.getMenu();
		RecipeDto recipeDto = service.getRecipeService(rcode);
		System.out.println("rcode= " + recipeDto.getRcode());
		List<RecipeIngredientDto> recipeIngredientDto = service.getRecipeIngredientService(rcode);
		List<RecipeOrderDto> recipeOrderDto = service.getRecipeOrderService(rcode);
		RecipeTagDto recipeTagDto = service.getRecipeTagService(rcode);
		
		model.addAttribute("list", menuList);
		model.addAttribute("recipe", recipeDto);
		model.addAttribute("recipeIngredient", recipeIngredientDto);
		model.addAttribute("recipeOrder", recipeOrderDto);
		model.addAttribute("recipeTag", recipeTagDto);
		
		return "mypage2/recipe_update";
	}
	
	// 레시피 수정
	@PostMapping("/update")
	public String recipeUpdate(@RequestParam(name = "mcode", required = false) Integer mcode,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("introduce") String introduce,
			@RequestParam("url") String url,
			@RequestParam("category") String category,
			@RequestParam("portions") int portions,
			@RequestParam("icode") List<Integer> icode,
			@RequestParam("amount") List<Integer> amount,
			@RequestParam("orderNum") List<Integer> orderNum,
			@RequestParam("orderContent") List<String> orderContent,
			@RequestPart("cookingImg") List<MultipartFile> cookingImg,
			@RequestParam("tagContent") String tagContent,
			@RequestParam(name = "rcode", required = true) int rcode,
			Model model) {
		
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto mno = loginService.getMnoByUsernameService(username);
		System.out.println("mno = " + mno.getMno());
		System.out.println("rcode = " + rcode);
		
		// recipe 테이블 수정
		RecipeDto recipeDto = new RecipeDto();
		if(mcode != null) {
			recipeDto.setMcode(mcode);
		}else {
			model.addAttribute("notMcode", "메뉴를 선택해 주세요.");
			return "redirect:/updateForm";
		}
		recipeDto.setRcode(rcode);
		recipeDto.setMno(mno.getMno());
		recipeDto.setRecipeName(recipeName);
		recipeDto.setIntroduce(introduce);
		if(url.length() > 12) {
			recipeDto.setUrl(url.substring(url.length() - 11));
		}else {
			recipeDto.setUrl(url);
		}
		recipeDto.setCategory(category);
		recipeDto.setPortions(portions);
		service.updateMyRecipeService(recipeDto);
		System.out.println(recipeDto);
		
		
		// 레시피 재료 등록 재료가 두개이상이면 List로 등록
		for(int i = 0; i < icode.size(); i ++) {
			
			RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
			recipeIngredientDto.setRcode(rcode);
			recipeIngredientDto.setIcode(icode.get(i));
			recipeIngredientDto.setAmount(amount.get(i));
			service.updateMyRecipeIngredientService(recipeIngredientDto);
			
			System.out.println(recipeIngredientDto);
		}
		
		// 요리순서 등록 순서가 두개 이상이면 List로 등록
		for(int i = 0; i < orderNum.size(); i++) {
			
			RecipeOrderDto recipeOrderDto = new RecipeOrderDto();
			recipeOrderDto.setRcode(rcode);
			recipeOrderDto.setOrderContent(orderContent.get(i));
			recipeOrderDto.setOrderNum(orderNum.get(i));
			
			if(!cookingImg.get(i).isEmpty()) {
				try {
					String cookimgImgName = FileUploadUtil.saveFile(cookingImg.get(i).getOriginalFilename(), cookingImg.get(i));
					recipeOrderDto.setCookingImg(cookimgImgName);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}else{
				
			}
			
			service.updateMyRecipeOrderService(recipeOrderDto);
			System.out.println(recipeOrderDto);
			
		}
		
		// 태그 등록
		RecipeTagDto recipeTagDto = new RecipeTagDto();
		recipeTagDto.setRcode(rcode);
		recipeTagDto.setTagContent(tagContent);
		service.updateMyRecipeTagService(recipeTagDto);
		System.out.println(recipeTagDto);
		
		return "redirect:list?mno=" + mno.getMno();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
