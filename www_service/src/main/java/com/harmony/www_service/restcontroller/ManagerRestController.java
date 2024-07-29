package com.harmony.www_service.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.service.IngredientService_dally;
import com.harmony.www_service.service.MenuReqService;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {

	@Autowired
	IngredientService_dally iService;
	@Autowired
	MenuReqService mrService;
	
	// 재료 리스트 조회 기능
	@GetMapping("/list")
	public List<IngredientDto> list(@RequestParam("category") String category){
		//System.out.println("...........재료 리스트 컨트롤러 진입............");
		List<IngredientDto> result = iService.showListByCategory(category);
		//System.out.println("result="+ result);
		return result;
	}
	
	
}
