package com.harmony.www_service.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		System.out.println("...........컨츠롤러에서 보냄............");
		
		return result;
	}
	

	
}
