package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDtoWithFile;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.service.DashBoardService;
import com.harmony.www_service.service.IngredientService_dally;
import com.harmony.www_service.service.MenuReqService;

@Controller
public class ManagerController {

	@Autowired
	IngredientService_dally iService;
	@Autowired
	MenuReqService mrService;
	@Autowired
	DashBoardService dbService;
	//@Autowired
	//private RedisTemplate<String, Object> redisTemplate;
	
	private static final String VISITOR_COUNT_KEY = "visitorCount";
	
	@GetMapping(value = "/manager")
	public String managerMain() {
		return "manager/main";
	}

	// 재료 등록 페이지
	@RequestMapping("/ingredients_regist")
	public String goRegist() {

		return "manager/ingredients_regist";
	}

	// 재료 등록 기능
	@PostMapping("/do_ingredients_regist")
	public String ingredientRegist(IngredientDtoWithFile iDtoFile) {

		iService.addIngredients(iDtoFile);
		return "redirect:/ingredients_list";
	}

	// 재료 리스트 페이지
	@RequestMapping("/ingredients_list")
	public String goList() {

		return "manager/ingredients_list";
	}

	// 재료 디테일 페이지
	@RequestMapping("/ingredient_detail")
	public String goDetail(@RequestParam("icode") int icode, Model model) {
		IngredientDto result = iService.showDetail(icode);
		model.addAttribute("detail", result);

		return "manager/ingredient_detail";
	}

	// 재료 수정 페이지
	@RequestMapping("/ingredient_update")
	public String updatePage(@RequestParam("icode") int icode, Model model) {
		IngredientDto result = iService.showDetail(icode);
		model.addAttribute("detail", result);

		return "manager/ingredients_update";
	}

	// 재료 수정 기능
	@RequestMapping("/do_ingredient_update")
	public String update(IngredientDtoWithFile iDtoFile) {
		iService.updateIngredients(iDtoFile);
		int icode = iDtoFile.getIcode();

		return "redirect:/ingredient_detail?icode=" + icode;
	}

	// 재료 삭제 기능 구현
	@RequestMapping("/do_ingredient_delete")
	public String delete(@RequestParam("icode") int icode) {
		iService.IgredientIsGone(icode);

		return "redirect:/ingredients_list";
	}

	// -------------------------- 요청 메뉴 -------------------------------

	// 요청받은 메뉴 리스트
	@RequestMapping("/menu_req_list")
	public String getReqMenuList(Model model) {
		List<MenuReqDto> lists = mrService.getReqMenuList();
		model.addAttribute("list", lists);
		System.out.println("^^^^^^^^^^^^^^^^^^^^" + lists);

		return "manager/menu_approval";
	}

	// 요청 메뉴 처리 - 승인
	@RequestMapping("/do_access")
	public String accessMenuReq(@RequestParam("mrcode") int mrcode,
			@RequestParam("menuName") String menuName,
			@RequestParam("category") String category,
			@RequestParam("imgurl") String imgurl
			) {
		MenuDto mDto = new MenuDto();
		mDto.setMenuName(menuName);
		mDto.setImgurl(imgurl);
		mDto.setCategory(category);
		mrService.addMenu(mDto);
		mrService.deleteMenu(mrcode);

		return "redirect:/menu_req_list";
	}

	// 요청 메뉴 처리 - 반려
	@RequestMapping("/do_return")
	public String returnMenuReq(@RequestParam("mrcode") int mrcode) {
		mrService.deleteMenu(mrcode);
		return "redirect:/menu_req_list";
	}
	
	// ----------------------------- 대시보드 ------------------------------
	
	
	
//	@GetMapping("/dashboard")
//	public String index(Model model) {
//		// 방문자수 증가
//		Long visitorCount = redisTemplate.opsForValue().increment(VISITOR_COUNT_KEY, 1);
//		
//		// 모델에 방문자 수 실어보내기
//		model.addAttribute("visitorCount", visitorCount);
//		return "redirect:/manager";
//	}
//	
//		
//	
//	// 연령대별 회원수 통계
//	public String memsByAges(Model model) {
//		List<MemberDtoForDashBoard> result = dbService.NumberOfMemsByAgeGroup();
//		model.addAttribute("list", result);
//		return "redirect:/manager";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}