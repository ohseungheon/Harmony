package com.harmony.www_service.dto;


import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberDto_by {

	private int mno;
	private String username;
	private String password;
	private String nickName;
	private String addr;
	private LocalDate birth;
	private String phone;
}
