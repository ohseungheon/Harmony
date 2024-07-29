package com.harmony.www_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harmony.www_service.dto.GenderDtoForDashBoard;
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
	// @Autowired
	// private RedisTemplate<String, Object> redisTemplate;

	private static final String VISITOR_COUNT_KEY = "visitorCount";

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
	public String accessMenuReq(@RequestParam("mrcode") int mrcode, @RequestParam("menuName") String menuName,
			@RequestParam("category") String category, @RequestParam("imgurl") String imgurl) {
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

	@RequestMapping("/dashboard")
	public String index(Model model) {

//		// 방문자수 증가 (redis)
//		Long visitorCount = redisTemplate.opsForValue().increment(VISITOR_COUNT_KEY, 1);
//		
//		// 모델에 방문자 수 실어보내기
//		model.addAttribute("visitorCount", visitorCount);
//		return "redirect:/manager";
//		-----------------------------------------------------------------------------		

		// 총 회원수
		int totalMemberCnt = dbService.findAll();
		model.addAttribute("totalMemberCount", totalMemberCnt);

		// 새 회원 수 (일별)
		int newDayMemberCount = dbService.findNewDayMember();
		model.addAttribute("newDayMemberCount", newDayMemberCount);

		// 새 회원 수 (월별)
		int newMonthMemberCount = dbService.findNewMonthMember();
		model.addAttribute("newMonthMemberCount", newMonthMemberCount);

		// 회원 정보 + 회원이 등록한 레시피 수
//		List<MemberDtoForDashBoard> memberInfo = dbService.findMemberInfo();
//		model.addAttribute("memberInfo", memberInfo);

		return "manager/main";
	}

	@GetMapping("/getAgeGroups")
	@ResponseBody
	public Map<String, Object> getAgeGroups() {

		// 연령대별 회원수 통계
		int[] ageGroups = { 0, 0, 0, 0, 0, 0 };
		String[] ageTitle = { "10대", "20대", "30대", "40대", "50대", "60대" };
		int[] ages = dbService.findMemByAges();

		for (int age : ages) {
			if (age >= 0 && age < 20) {
				ageGroups[0]++;
				System.out.println("ageGroups[0] :" + ageGroups[0]);
			} else if (age >= 20 && age < 30) {
				ageGroups[1]++;
				System.out.println("ageGroups[1] :" + ageGroups[1]);
			} else if (age >= 30 && age < 40) {
				ageGroups[2]++;
				System.out.println("ageGroups[2] :" + ageGroups[2]);
			} else if (age >= 40 && age < 50) {
				ageGroups[3]++;
				System.out.println("ageGroups[3] :" + ageGroups[3]);
			} else if (age >= 50 && age < 60) {
				ageGroups[4]++;
				System.out.println("ageGroups[4] :" + ageGroups[4]);
			} else if (age >= 60 && age < 70) {
				ageGroups[5]++;
				System.out.println("ageGroups[5] :" + ageGroups[5]);
			}
		}

		// 연령대 제목과 데이터를 동시에 view로 보내기
		Map<String, Object> ageResponse = new HashMap<>();
		ageResponse.put("ageGroups", ageGroups);
		ageResponse.put("ageTitle", ageTitle);

		return ageResponse;
	}

	@GetMapping("/getGenderCnt")
	@ResponseBody
	public Map<String, Object> getGenderCount() {

		// 성별 회원수 통계
		String[] genderTitle = { "남성", "여성" };
		GenderDtoForDashBoard genderCountDto = dbService.findMemByGender();
		
		// 성별 데이터 배열로 변환
		int[] genderCount = {genderCountDto.getMale_ratio(), genderCountDto.getFemale_ratio()};
		
		// 성별 제목과 데이터를 동시에 view로 보내기
		Map<String, Object> genderResponse = new HashMap<>();
		genderResponse.put("genderTitle", genderTitle);
		genderResponse.put("genderCount", genderCount);
		
		return genderResponse;
	}

}