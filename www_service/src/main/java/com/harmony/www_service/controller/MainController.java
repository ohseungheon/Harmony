package com.harmony.www_service.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.service.LoginService;

@Controller
public class MainController {
	
	@Autowired
	private LoginService loginService;
	
	 @RequestMapping("/")
	    public String root(Model model) {
	        
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        String role = null;
	        if (authorities != null && !authorities.isEmpty()) {
	            Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	            if (iter.hasNext()) {
	                GrantedAuthority auth = iter.next();
	                role = auth.getAuthority();
	            }
	        }
	        
	        MemberDto memberDto = loginService.getMnoByUsernameService(username);
	        if (memberDto != null) {
	            model.addAttribute("mno", memberDto.getMno());
	        } else {
	            model.addAttribute("mno", "No mno found");
	        }
	        
	        model.addAttribute("username", username);
	        model.addAttribute("role", role != null ? role : "No role found");
	        
	        return "main/home";
	    }
}
