package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDtoWithFile;
import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.service.IngredientService_dally;
import com.harmony.www_service.service.MenuReqService;

@Controller
public class ManagerController {

	@Autowired
	IngredientService_dally iService;
	@Autowired
	MenuReqService mrService;
	
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

	// 요청받은 메뉴 리스트
	@RequestMapping("/menu_req_list")
	public String getList() {
		return "manager/menu_approval";
	}

	// 요청 메뉴 리스트 조회 기능
	@GetMapping("/menuReqlist")
	public List<MenuReqDto> getList() {
		System.out.println("menuRestController 진입-----------------");
		List<MenuReqDto> list = mrService.getReqMenuList();

		return list;
	}

	// (승인 시)요청 메뉴를 정식 메뉴로 등록
	@PostMapping("/addMenuReq")
	public void addMenuReq(@RequestParam("mrDto") MenuReqDto mrDto) {
		System.out.println("~~~~~~~ menuReqRestController 진입 ~~~~~~~");
		int result = mrService.addMenu(mrDto);

	}

	// (승인 or 반려 시)요청메뉴를 요청메뉴테이블에서 삭제
	@DeleteMapping("/deleteMenuReq")
	public void deleteMenuReq(int mrcode) {
		System.out.println("+++++++++ delete rests 진입 ++++++++++");
		int result = mrService.deleteMenu(mrcode);
	}

}