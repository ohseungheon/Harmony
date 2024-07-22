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
	public String getReqMenuList() {
		return "manager/menu_approval";
	}

	// 요청 메뉴 리스트 조회 기능
	@RequestMapping("/do_menu_req_list")
	public String getList(Model model) {
		System.out.println("menuController 진입-----------------");
		List<MenuReqDto> lists = mrService.getReqMenuList();
		if(lists == null) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
		}
		model.addAttribute("list", lists);
		System.out.println("^^^^^^^^^^^^^^^^^^^^" + lists);
		
		return "manger/menu_approval";
	}

	// 요청 메뉴 처리 - 승인 / 반려
	@RequestMapping("/do_reqMenu_process")
	public String accessMenuReq(@RequestParam("mrDto") MenuReqDto mrDto) {

		String state = mrDto.getState();
		
		if (state.equals("승인")) {
			// 요청 메뉴를 정식 메뉴로 등록
			System.out.println("~~~~~~~ 메뉴 등록 컨트롤러 ~~~~~~~");
			int result_add = mrService.addMenu(mrDto);
			if (result_add == 1) {
				System.out.println("정식 메뉴 등록 성공");
			} else {
				System.out.println("정식 메뉴 등록 실패");
			}

			// 요청메뉴를 요청메뉴테이블에서 삭제
			System.out.println("+++++++++ 요청 메뉴 삭제 컨트롤러 ++++++++++");
			int result_delete = mrService.deleteMenu(mrDto.getMrcode());
			if (result_delete == 1) {
				System.out.println("메뉴 삭제 성공");
			} else {
				System.out.println("메뉴 삭제 실패");
			}
			
		}else if(state.equals("반려")) {
			// 요청메뉴를 요청메뉴테이블에서 삭제
			System.out.println("+++++++++ 요청 메뉴 삭제 컨트롤러 ++++++++++");
			int result_delete = mrService.deleteMenu(mrDto.getMrcode());
			if (result_delete == 1) {
				System.out.println("메뉴 삭제 성공");
			} else {
				System.out.println("메뉴 삭제 실패");
			}
			
		}
		
		return "redirect:/menu_req_list";
	}

}