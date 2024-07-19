package com.harmony.www_service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;
import com.harmony.www_service.service.LoginService;
import com.harmony.www_service.service.MyRecipeService;
import com.harmony.www_service.util.FileUploadUtil;
import com.harmony.www_service.util.YoutubeUrlUtil;

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
		List<IngredientDto> ingredientList = service.getIngredientService();
		System.out.println(ingredientList);
		model.addAttribute("ingredientList", ingredientList);
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
		recipeDto.setUrl(url);
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
	
	// 레시피 수정 폼
	@GetMapping("/updateForm")
	@Transactional
	public String updateForm(Model model,
			@RequestParam("rcode") int rcode) {
		
		List<MenuDto> menuList = menuDao.getMenu();
		model.addAttribute("list", menuList);
		
		RecipeDto recipeDto = service.getRecipeService(rcode);
		System.out.println("rcode= " + recipeDto.getRcode());
		model.addAttribute("recipe", recipeDto);
		
		List<RecipeIngredientDto> recipeIngredientDto = service.getRecipeIngredientService(rcode);
		model.addAttribute("recipeIngredient", recipeIngredientDto);
		
		List<RecipeOrderDto> recipeOrderDto = service.getRecipeOrderService(rcode);
		model.addAttribute("recipeOrder", recipeOrderDto);
		
		RecipeTagDto recipeTagDto = service.getRecipeTagService(rcode);
		if(recipeTagDto != null) {
			model.addAttribute("recipeTag", recipeTagDto);
		}
		
		List<IngredientDto> ingredientList = service.getIngredientService();
		model.addAttribute("ingredientList", ingredientList);
		
		
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
		recipeDto.setUrl(url);
		recipeDto.setCategory(category);
		recipeDto.setPortions(portions);
		service.updateMyRecipeService(recipeDto);
		System.out.println(recipeDto);
		
		

	    // 레시피 재료 수정
		List<RecipeIngredientDto> existingIngredients = service.getRecipeIngredientService(rcode);
		List<Integer> updatedIngredientIds = new ArrayList<>();

		for (int i = 0; i < icode.size(); i++) {
		    RecipeIngredientDto ingredientDto = new RecipeIngredientDto();
		    ingredientDto.setRcode(rcode);
		    ingredientDto.setIcode(icode.get(i));
		    ingredientDto.setAmount(amount.get(i));

		    if (i < existingIngredients.size()) {
		        // 기존 재료 업데이트
		        int ricode = existingIngredients.get(i).getRicode();
		        ingredientDto.setRicode(ricode);
		        service.updateMyRecipeIngredientService(ingredientDto);
		        updatedIngredientIds.add(ricode);
		    } else {
		        // 새로운 재료 추가
		        int newRicode = service.registMyRecipeIngredientService(ingredientDto);
		        updatedIngredientIds.add(newRicode);
		    }
		}

		// 삭제된 재료 처리
		for (RecipeIngredientDto existingIngredient : existingIngredients) {
		    if (!updatedIngredientIds.contains(existingIngredient.getRicode())) {
		        service.deleteMyRecipeIngredientService(existingIngredient.getRicode());
		    }
		}

		
	 // 요리순서 수정
	    List<RecipeOrderDto> existingOrders = service.getRecipeOrderService(rcode);
	    for (int i = 0; i < orderNum.size(); i++) {
	        RecipeOrderDto orderDto = new RecipeOrderDto();
	        orderDto.setRcode(rcode);
	        orderDto.setOrderContent(orderContent.get(i));
	        orderDto.setOrderNum(orderNum.get(i));

	        if (!cookingImg.get(i).isEmpty()) {
	            try {
	                String cookingImgName = FileUploadUtil.saveFile(cookingImg.get(i).getOriginalFilename(), cookingImg.get(i));
	                orderDto.setCookingImg(cookingImgName);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else if (i < existingOrders.size()) {
	            // 기존 이미지 유지
	            orderDto.setCookingImg(existingOrders.get(i).getCookingImg());
	        }

	        if (i < existingOrders.size()) {
	            // 기존 순서 업데이트
	            orderDto.setRocode(existingOrders.get(i).getRocode());
	            service.updateMyRecipeOrderService(orderDto);
	        } else {
	            // 새로운 순서 추가
	            service.registMyRecipeOrderService(orderDto);
	        }
	    }
	    // 삭제된 순서 처리
	    if (orderNum.size() < existingOrders.size()) {
	        for (int i = orderNum.size(); i < existingOrders.size(); i++) {
	            service.deleteMyRecipeOrderService(existingOrders.get(i).getRocode());
	        }
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
