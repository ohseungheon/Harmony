package com.harmony.www_service.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.service.MenuReqService;

@RestController
@RequestMapping("/manager")
public class MenuReqRestcontroller {

	@Autowired
	MenuReqService mrService;
	
	@GetMapping("/menuReqlist")
	public List<MenuReqDto> getList(@RequestParam("mrcode") int mrcode){
		System.out.println("menuRestController 진입-----------------");
		List<MenuReqDto> list = mrService.getReqMenuList(mrcode);
		
		return list;
	}
}
