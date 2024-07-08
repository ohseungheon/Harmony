package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.service.MemberService;

@RestController
public class MemberRestController {
	@Autowired
	MemberService memberService;
	
	@PostMapping("/info_update")
	public ResponseEntity<String> infoUpdate(@RequestBody MemberDto member) {
	    memberService.updateMemberInfo(member);
	    return ResponseEntity.ok("회원정보가 성공적으로 수정되었습니다😎");
	}
	
}
