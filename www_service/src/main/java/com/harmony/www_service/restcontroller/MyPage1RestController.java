package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.IMypage1Dao;
import com.harmony.www_service.dto.MemberDto_by;
import com.harmony.www_service.service.MemberService;

@RestController
@RequestMapping("/mypage1")
public class MyPage1RestController {
	@Autowired
	MemberService memberService;
	@Autowired
	IMypage1Dao myDao;
	
	@PostMapping("/info_update")
	public ResponseEntity<String> infoUpdate(@RequestBody MemberDto_by member) {
	    memberService.updateMemberInfo(member);
	    return ResponseEntity.ok("회원정보가 성공적으로 수정되었습니다😎");
	}
	
	
	@DeleteMapping("/material/{fcode}")
	public String deleteMaterial( @PathVariable("fcode") int fcode) {
		System.out.println("삭제fcode"+fcode);
		
		myDao.deleteMaterial(fcode);
		
		return "해당 재료가 삭제되었습니다🍳";
	}
	
}
