package com.harmony.www_service.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class UserMemberDto {
	
	private String username;
	private String password;
	private String role;
	private String nickName;
	private String addr;
	private LocalDate birth;
	private String phone;
	private String gender;
	private Date joinDate;

}
