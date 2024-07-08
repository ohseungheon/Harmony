package com.harmony.www_service.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private int mno;
	private String username;
	private String nickName;
	private String addr;
	private LocalDate birth;
	private String phone;
}
