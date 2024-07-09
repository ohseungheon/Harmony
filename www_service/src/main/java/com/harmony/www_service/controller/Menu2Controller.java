package com.harmony.www_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.MenuReqWithMember;
import com.harmony.www_service.dto.MenuRequestWithFile;
import com.harmony.www_service.service.MemberService;
import com.harmony.www_service.service.Menu2Service;



@Controller
@RequestMapping("/menu2")
public class Menu2Controller {

    @Autowired
    Menu2Service service;

    @Autowired
    MemberService memberService;
    
    @RequestMapping("/addForm")
    public String addRequestForm() {
        
        return "menu2/menu_request";
    }

    @PostMapping("/add")
    public String postMethodName( MenuRequestWithFile reqMenu) {
        String view = "redirect:/menu2/menu-request-list";
        
        // security 내부의 로그인 되어 있는 아이디( username ) 불러오기
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); 

        // username으로 회원 정보 테이블의 mno 불러오기
        Optional<Integer> mno_ = memberService.getMnoByUsername(username);
        if(mno_.isPresent()){ // 로그인된 아이디로 회원 정보가 존재하면
            // 메뉴 추가 요청 등록
            service.addMenuRequest(reqMenu,mno_.get()); 
        }
        else{
            // 로그인된 아이디의 회원 정보가 없으면 로그인 페이지로
            view = "redirect:/login_page";
        }

        return view; 
    }
    
    @GetMapping("/menu-request-list")
    public String menuRequestTest(Model model){
        List<MenuReqWithMember> list = service.getAllMenuReqDto();
        model.addAttribute("mrList", list);
        
        return "menu2/menu_request_list";
    }
}
