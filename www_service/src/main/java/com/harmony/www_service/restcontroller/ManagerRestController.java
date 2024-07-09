package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.service.IngredientService_dally;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {

	@Autowired
	IngredientService_dally iService;
	
	
//	@PostMapping("/ingredients")
//	public void Ingredient(@RequestBody IngredientDto iDto) {
//		iService.save(iDto);
//	}
	
	
	
	
	
	
	
}
