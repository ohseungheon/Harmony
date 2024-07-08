package com.harmony.www_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class ApiLoginController {

	@PostMapping("/doLogin")
	public String login(@RequestBody String username,
			@RequestBody String password) {
		
		
		
		return "";
	}
	
}
