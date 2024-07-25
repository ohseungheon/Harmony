package com.harmony.www_service.dto;


import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class MemberDto {

	private int mno;
	private String username;
	private String nickName;
	private String addr;
	private LocalDate birth;
	private String phone;
	private String gender;
	private Date joinDate;
}
