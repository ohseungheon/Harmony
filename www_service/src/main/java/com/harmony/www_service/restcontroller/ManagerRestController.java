package com.harmony.www_service.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.service.IngredientService_dally;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {

	@Autowired
	IngredientService_dally iService;
	
	
	// 재료 리스트 기능 구현
	@GetMapping("/list")
	public List<IngredientDto> list(@RequestParam("category") String category){
		System.out.println("...........재료 리스트 컨트롤러 진입............");
		
		List<IngredientDto> result = iService.showList(category);
		
		return result;
	}
	
	// 재료 수정 기능 구현
	@PutMapping("/new-ingredient")
	public IngredientDto update(IngredientDto iDto){
		System.out.println("--------update--------");
		IngredientDto update_result = iService.updateIngredient(iDto);
		
		System.out.println("****************iDto : " + iDto);
		
		return update_result;
	}


}
