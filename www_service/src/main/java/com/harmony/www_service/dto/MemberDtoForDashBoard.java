package com.harmony.www_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberDtoForDashBoard {

	private int mno;
	private String username;
	private String nickName;
	private String addr;
	private LocalDate birth;
	private int age;
	private String phone;
	private String gender;
	private LocalDate joinDate;
//	private int recipeCnt;
//	private int newMemberCntPerDay;
//	private int newMemberCntPerMon;
//	private double genderRatio;
//	private int memberCntByAge;
	
}
