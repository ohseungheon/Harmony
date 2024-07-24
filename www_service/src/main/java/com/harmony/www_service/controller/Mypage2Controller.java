package com.harmony.www_service.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;
import com.harmony.www_service.dto.RecipeUpdateForm;
import com.harmony.www_service.dto.RecipeWithMenuDto;
import com.harmony.www_service.service.LoginService;
import com.harmony.www_service.service.MyRecipeService;
import com.harmony.www_service.util.FileUploadUtil;

@Controller
@RequestMapping("/my/recipe")
public class Mypage2Controller {

	private static final Logger logger = LoggerFactory.getLogger(Mypage2Controller.class);

	@Autowired
	private MyRecipeService service;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RecipeMenuDao menuDao;

	@Autowired
	private RecipeDao recipeDao;
	
	@GetMapping("/list")
	public String myRecipeList(@RequestParam("mno") int mno,
			Model model) {
		
        List<RecipeDto> recipeList = service.myRecipeService(mno);
        model.addAttribute("recipeList", recipeList);
        
        List<RecipeWithMenuDto> recipesWithMenu = service.getRecipesWithMenu(mno);
        model.addAttribute("recipesWithMenu", recipesWithMenu);
        
     // 각 레시피의 마지막 요리 이미지를 가져옵니다.
        Map<Integer, String> lastImgMap = new HashMap<>();
        for (RecipeDto recipe : recipeList) {
            RecipeOrderDto lastImg = recipeDao.recipeLastCookImg(recipe.getRcode());
            lastImgMap.put(recipe.getRcode(), lastImg.getLastCookingImg());
        }
        
        model.addAttribute("lastImg", lastImgMap);
        
        return "mypage2/my_recipe_list";
    }


	// 레시피 등록 폼
	@RequestMapping("/registForm")
	public String myRecipeRegist(Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto member = loginService.getMnoByUsernameService(username);
		int mno = member.getMno();
		model.addAttribute("mno", mno);
		System.out.println("update mno : " + mno);

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
	public String recipeRegist(@RequestParam("mcode") int mcode, @RequestParam("recipeName") String recipeName,
			@RequestParam("introduce") String introduce, @RequestParam("url") String url,
			@RequestParam("category") String category, @RequestParam("portions") int portions,
			@RequestParam("icode") List<Integer> icode, @RequestParam("amount") List<Integer> amount,
			@RequestParam("orderNum") List<Integer> orderNum, @RequestParam("orderContent") List<String> orderContent,
			@RequestPart("cookingImg") List<MultipartFile> cookingImg, @RequestParam("tagContent") String tagContent,
			Model model) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto mno = loginService.getMnoByUsernameService(username);
		System.out.println("mno = " + mno.getMno());
		model.addAttribute("mno", mno);

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

		// int rcode = recipeDto.getRcode();
		// System.out.println("rcode = " + rcode);

		// 레시피 재료 등록 재료가 두개이상이면 List로 등록
		for (int i = 0; i < icode.size(); i++) {

			RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
			recipeIngredientDto.setRcode(recipeDto.getRcode());
			recipeIngredientDto.setIcode(icode.get(i));
			recipeIngredientDto.setAmount(amount.get(i));
			service.registMyRecipeIngredientService(recipeIngredientDto);

		}

		// 요리순서 등록 순서가 두개 이상이면 List로 등록
		for (int i = 0; i < orderNum.size(); i++) {

			RecipeOrderDto recipeOrderDto = new RecipeOrderDto();
			recipeOrderDto.setRcode(recipeDto.getRcode());
			recipeOrderDto.setOrderContent(orderContent.get(i));
			recipeOrderDto.setOrderNum(orderNum.get(i));

			if (!cookingImg.get(i).isEmpty()) {
				try {
					String cookimgImgName = FileUploadUtil.saveFile(cookingImg.get(i).getOriginalFilename(),
							cookingImg.get(i));
					recipeOrderDto.setCookingImg(cookimgImgName);
				} catch (IOException e) {
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
	public String updateForm(Model model, @RequestParam("rcode") int rcode) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		MemberDto member = loginService.getMnoByUsernameService(username);
		int mno = member.getMno();
		model.addAttribute("mno", mno);
		System.out.println("update mno : " + mno);
		
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
		if (recipeTagDto != null) {
			model.addAttribute("recipeTag", recipeTagDto);
		}

		List<IngredientDto> ingredientList = service.getIngredientService();
		model.addAttribute("ingredientList", ingredientList);

		logger.info("Recipe DTO: {}", recipeDto);

		return "mypage2/recipe_update";
	}
	

	// 레시피 수정
	@PostMapping("/update")
	public String recipeUpdate(@ModelAttribute RecipeUpdateForm form, Model model) {

		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			MemberDto member = loginService.getMnoByUsernameService(username);
			int mno = member.getMno();
			model.addAttribute("mno", mno);
			System.out.println("update mno : " + mno);

			if (form.getMcode() == null) {
				model.addAttribute("error", "메뉴를 선택해 주세요.");
				return "updateForm";
			}

			RecipeDto recipeDto = form.toRecipeDto(member.getMno());
			logger.info("Recipe DTO before update: {}", recipeDto);
			List<RecipeIngredientDto> ingredients = form.toRecipeIngredientDtoList();
			List<RecipeOrderDto> orders = form.toRecipeOrderDtoList();
			RecipeTagDto recipeTagDto = form.toRecipeTagDto();

			service.updateEntireRecipe(recipeDto, ingredients, orders, form.getCookingImg(), form.getExistingCookingImg(), recipeTagDto);

			return "redirect:list?mno=" + member.getMno();
		} catch (Exception e) {
			logger.error("Failed to update recipe: ", e);
			model.addAttribute("error", "레시피 수정에 실패했습니다.");
			return "errorPage";
		}
	}

}
