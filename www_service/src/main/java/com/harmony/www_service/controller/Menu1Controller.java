package com.harmony.www_service.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.ui.Model;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dto.FridgeIngredientDto;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDto2;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.service.MenuService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/menu1")
public class Menu1Controller {
	@Autowired
	Menu1Dao menu1dao;
	@Autowired
	MenuService menuService;

	@RequestMapping("/main")
	public String main(Model model, HttpSession session,@RequestParam(name = "checked", required = false, defaultValue = "false") boolean checked) {
		System.out.println("==================================main===========================");

		// 사용하지 않을 재료 리스트
		session.removeAttribute("NoInFridgeIngredientList");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int mno = menu1dao.getMno(username); // id로 mno값 가져옴

		// 사용자 냉장고에 들어있는 재료
		List<IngredientDto> FridgeIngredientList = menu1dao.showFridgeIngredient(mno);
		model.addAttribute("FridgeIngredientList", FridgeIngredientList);
		List<IngredientDto2> FridgeIngredientListForDto = menu1dao.showFridgeIngredientForDto(mno);
		
		
		model.addAttribute("FridgeIngredientList", FridgeIngredientList);
		model.addAttribute("FridgeIngredientListForDto", FridgeIngredientListForDto);

		// 메뉴1 화면에서 사용할 사용자가 가지고 있는 재료를 세션에 저장
		session.setAttribute("InFridgeIngredientList", FridgeIngredientList);

		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList"); // 세션에서 사용하지 않을 재료 리스트 불러옴

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}
		
		List<Long> days = new ArrayList<>();
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		
		for(IngredientDto2 i : FridgeIngredientListForDto) {
			long dday = -1*(ChronoUnit.DAYS.between(i.getDeadline(), now));
			
			days.add(dday);
			
			
		}
		

		// 가지고 있는 재료로 만들 수 있는 메뉴 리스트
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(FridgeIngredientList);
		List<MenuDto> showCanMakeMenuList2 = menuService.getCanMakeMenu2(FridgeIngredientList);
		
//		List<Integer> rcodeList = new ArrayList<>();
//		for (MenuDto menu:showCanMakeMenuList2) {
//			rcodeList.add(menu.getMcode())
//		}
		
		model.addAttribute("showCanMakeMenuList", showCanMakeMenuList);
		model.addAttribute("showCanMakeMenuList2", showCanMakeMenuList2);
		model.addAttribute("NoInFridgeIngredientList", NoInFridgeIngredientList);
		model.addAttribute("days", days);
		
		
		//menu1dao.showFridgeIngredientIcodeList(mno);
		//menu1dao.showRecipeIngredientIcodeList();
		//menu1dao.countIntersection(null, null);
		return "menu1/menu_harmony1";
	}

	@ResponseBody
	@RequestMapping("/reset")
	public String reset(org.springframework.ui.Model model, HttpSession session) {
		session.removeAttribute("NoInFridgeIngredientList");

		return "mypage1/mypage_main";
	}

	@ResponseBody
	@GetMapping("/deleteFridgeIngredientList") // 재료삭제후 가진 재료 리스트
	public List<IngredientDto> deleteFridgeIngredientList(@RequestParam("icode") int icode, HttpSession session) {
		System.out.println("===========================deleteFridgeIngredientList=========================");

		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList"); // 세션에서 사용하지 않을 재료 리스트 불러옴

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}
		session.setAttribute("NoInFridgeIngredientList", NoInFridgeIngredientList); // 세션에 사용하지 않을 재료 리스트 저장

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode); // icode에 해당하는 재료를 뽑음

		NoInFridgeIngredientList.add(getOneIngredient); // 뽑은 재료를 사용하지 않을 재료 리스트에 추가

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		// 사용할 수 있는 재료리스트를 불러옴

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		// 사용할 수 있는 재료리스트에서 뽑은 재료를 제거
		InFridgeIngredientList.remove(getOneIngredient);
		session.setAttribute("InFridgeIngredientList", InFridgeIngredientList);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int mno = menu1dao.getMno(username);

		// 냉장고에 없는 재료만 제외하고 사용자 냉장고에서 재료 출력해주는 dao 실행
		List<IngredientDto> selectExcludeIngredientList = menuService.selectExcludeIngredient(NoInFridgeIngredientList,
				mno);

		System.out.println("=================NoInFridgeIngredientList==================: " + NoInFridgeIngredientList);
		System.out.println("=================InFridgeIngredientList==================: " + InFridgeIngredientList);
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

		return selectExcludeIngredientList;
	}

	@ResponseBody
	@GetMapping("/deleteCanMakeMenu") // 삭제
	public List<MenuDto> deleteCanMakeMenu(@RequestParam("icode") int icode, HttpSession session) {
		// System.out.println("===========================deleteCanMakeMenu=========================");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//
		int mno = menu1dao.getMno(username);
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		// 사용하지 않을 재료를 사용할수 있는재료 리스트에서 제거
		for (IngredientDto NoInFridgeIngredient : NoInFridgeIngredientList) {

			InFridgeIngredientList.remove(NoInFridgeIngredient);
		}

		// 사용할수 있는 재료로 만들 수 있는 메뉴 보여주는 dao함수 실행
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

		return showCanMakeMenuList;
	}

	@ResponseBody
	@GetMapping("/mcodeListForIcodeList")
	public List<Integer> mcodeListForIcodeList(@RequestParam("icode") int icode, HttpSession session) {
		// System.out.println("===========================deleteCanMakeMenu=========================");
		
		System.out.println("***********************************************************************************");
		System.out.println("***********************************************************************************");
		System.out.println("==========================In mcodeListForIcodeList===========================");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//
		int mno = menu1dao.getMno(username);
		System.out.println("======================================= mno:"+mno);
		
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}
		System.out.println("============================In mcodeListForIcodeList NoInFridgeIngredientList"+NoInFridgeIngredientList);

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}
		System.out.println("============================In mcodeListForIcodeList InFridgeIngredientList"+InFridgeIngredientList);

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
		System.out.println("============================In mcodeListForIcodeList getOneIngredient"+getOneIngredient);

		// 사용하지 않을 재료를 사용할수 있는재료 리스트에서 제거
		for (IngredientDto NoInFridgeIngredient : NoInFridgeIngredientList) {

			InFridgeIngredientList.remove(NoInFridgeIngredient);
		}
		
		
		for (IngredientDto NoInFridgeIngredient : NoInFridgeIngredientList) {

			InFridgeIngredientList.remove(NoInFridgeIngredient);
		}
		
		System.out.println(" ");
		System.out.println("============================After In mcodeListForIcodeList InFridgeIngredientList"+InFridgeIngredientList);
		System.out.println(" ");

		// 사용할수 있는 재료로 만들 수 있는 메뉴 보여주는 dao함수 실행
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

		List<Integer> mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
		
		System.out.println("==========================test showCanMakeMenuList: " + showCanMakeMenuList);
		System.out.println("==========================test modeList: " + mcodeList);
		System.out.println("***********************************************************************************");
		System.out.println("***********************************************************************************");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("==========================InMcodeList NoInFridgeIngredient: " + NoInFridgeIngredientList);
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		return mcodeList;
	}

	@ResponseBody
	@GetMapping("/mcodeListForIcodeList2")
	public List<Integer> mcodeListForIcodeList2(@RequestParam("icode") int icode, HttpSession session) {
		// System.out.println("===========================deleteCanMakeMenu=========================");

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//
		int mno = menu1dao.getMno(username);

		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		// 사용하지 않을 재료를 사용할수 있는재료 리스트에서 제거
		for (IngredientDto NoInFridgeIngredient : NoInFridgeIngredientList) {

			InFridgeIngredientList.remove(NoInFridgeIngredient);
		}

		// 사용할수 있는 재료로 만들 수 있는 메뉴 보여주는 dao함수 실행
		List<Integer> mcodeList = new ArrayList<>();
		List<MenuDto> showCanMakeMenuList = new ArrayList<>();
		
		if (NoInFridgeIngredientList.size()==0) {
			showCanMakeMenuList = menuService.getCanMakeMenu2(InFridgeIngredientList);
			mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
			
		}else if(NoInFridgeIngredientList.size()!=0) {
			List<Integer>excludeIcode = menuService.makeIcodeList(NoInFridgeIngredientList);
		    showCanMakeMenuList = menuService.getCanMakeMenu2Exclude(InFridgeIngredientList,excludeIcode);
			mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
		
		}

		//List<Integer> mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
		//System.out.println("==========================test showCanMakeMenuList: " + showCanMakeMenuList);
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("==========================InMcodeList NoInFridgeIngredient: " + NoInFridgeIngredientList);
		System.out.println("==========================InMcodeList showCanMakeMenuList: " + showCanMakeMenuList);
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("      ");
		System.out.println("==========================test modeList: " + mcodeList);
		return mcodeList;
	}

	// 사용하지 않는 재료 리스트 반환 함수
	@ResponseBody
	@GetMapping("/NoUseIngredient")
	public List<IngredientDto> NoUseIngredient(HttpSession session) {
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");
		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}
		System.out.println(
				"===============================NoUseIngredient NoInFridgeIngredientList: " + NoInFridgeIngredientList);

		return NoInFridgeIngredientList;
	}

//   @ResponseBody // 재료 복원시 사용되는 함수
//   @GetMapping("/undoIngredient")
//   public List<IngredientDto> undoIngredient(@RequestParam("icode") int icode, HttpSession session) {
//      List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session.getAttribute("NoInFridgeIngredientList");
//      if (NoInFridgeIngredientList == null) {
//         NoInFridgeIngredientList = new ArrayList<>();
//      }
//      
//      //icode로 재료 뽑음
//      IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
//      
//      //사용하지 않는 재료 리스트에서 제거
//      NoInFridgeIngredientList.remove(getOneIngredient);
//      
//      List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session.getAttribute("InFridgeIngredientList");
//      if (InFridgeIngredientList == null) {
//         InFridgeIngredientList = new ArrayList<>();
//      }
//      
//      //사용할수 있는 재료 리스트에 추가
//      InFridgeIngredientList.add(getOneIngredient);
//      return NoInFridgeIngredientList;
//   }
//   

	@ResponseBody // 재료 복원시 사용되는 함수
	@GetMapping("/undoIngredient_menu1")
	public List<Integer> undoIngredient_menu1(@RequestParam("icode") int icode, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//
		int mno = menu1dao.getMno(username);

		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		NoInFridgeIngredientList.remove(getOneIngredient);
		InFridgeIngredientList.add(getOneIngredient);

		// 사용하지 않을 재료를 사용할수 있는재료 리스트에서 제거

		// 사용할수 있는 재료로 만들 수 있는 메뉴 보여주는 dao함수 실행
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

		List<Integer> mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
		System.out.println("==========================test showCanMakeMenuList: " + showCanMakeMenuList);
		System.out.println("==========================test modeList: " + mcodeList);
		return mcodeList;
	}

	@ResponseBody // 재료 복원시 사용되는 함수
	@GetMapping("/undoIngredient_menu2")
	public List<Integer> undoIngredient_menu2(@RequestParam("icode") int icode, HttpSession session) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//
		int mno = menu1dao.getMno(username);

		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		NoInFridgeIngredientList.remove(getOneIngredient);
		InFridgeIngredientList.add(getOneIngredient);

		// 사용하지 않을 재료를 사용할수 있는재료 리스트에서 제거

		// 사용할수 있는 재료로 만들 수 있는 메뉴 보여주는 dao함수 실행
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu2(InFridgeIngredientList);

		List<Integer> mcodeList = menuService.makeMcodeList(showCanMakeMenuList);
		System.out.println("==========================test showCanMakeMenuList: " + showCanMakeMenuList);
		System.out.println("==========================test modeList: " + mcodeList);
		return mcodeList;
	}

	@ResponseBody // 재료 복원시 사용되는 함수
	@GetMapping("/undoIngredient2")
	public List<MenuDto> undoIngredient2(@RequestParam("icode") int icode, HttpSession session) {
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");
		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		// icode로 재료 뽑음
		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		// 사용하지 않는 재료 리스트에서 제거
		NoInFridgeIngredientList.remove(getOneIngredient);

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");
		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}
		// 사용할수 있는 재료 리스트에 추가
		InFridgeIngredientList.add(getOneIngredient);
		List<MenuDto> undoCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);
		System.out.println(
				"================================undoCanMakeMenuList===================== " + undoCanMakeMenuList);
		return undoCanMakeMenuList;
	}

	@ResponseBody
	@GetMapping("/undoHavingIngredient")
	public List<IngredientDto> undoHavingIngredient(@RequestParam("icode") int icode, HttpSession session) {
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList");

		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}

		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
		NoInFridgeIngredientList.remove(getOneIngredient);
		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		// 리스트가 null일 경우 새로운 리스트를 생성합니다.
		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}

		// 뽑아낸 재료를 사용할 재료 리스트에 추가
		InFridgeIngredientList.add(getOneIngredient);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int mno = menu1dao.getMno(username);
		List<IngredientDto> selectExcludeIngredientList = menuService.selectExcludeIngredient(NoInFridgeIngredientList,
				mno);

		return selectExcludeIngredientList;
	}

	@ResponseBody
	@GetMapping("/undoCanMakeMenu")
	public List<MenuDto> undoCanMakeMenu(HttpSession session) {

		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList");

		// 리스트가 null일 경우 새로운 리스트를 생성합니다.
		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}
		List<MenuDto> undoCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

		return undoCanMakeMenuList;
	}

	@ResponseBody
	@GetMapping("/NoUseIngredientAllDelete")
	public String NoUseIngredientAllDelete(@Param("checked") Boolean checked, HttpSession session) {
		session.removeAttribute("NoInFridgeIngredientList");
		List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("NoInFridgeIngredientList"); // 세션에서 사용하지 않을 재료 리스트 불러옴
		List<IngredientDto> InFridgeIngredientList = (List<IngredientDto>) session
				.getAttribute("InFridgeIngredientList"); // 세션에서 사용하지 않을 재료 리스트 불러옴
		if (NoInFridgeIngredientList == null) {
			NoInFridgeIngredientList = new ArrayList<>();
		}
		if (InFridgeIngredientList == null) {
			InFridgeIngredientList = new ArrayList<>();
		}
		for (IngredientDto dto :NoInFridgeIngredientList ) {
			NoInFridgeIngredientList.remove(dto);
			InFridgeIngredientList.add(dto);
		}

		// 리스트가 null일 경우 새로운 리스트를 생성합니다.
		
		
		
		return "";
	}

	
	
	

}
