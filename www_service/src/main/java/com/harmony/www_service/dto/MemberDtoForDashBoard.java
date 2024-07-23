package com.harmony.www_service.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MemberDtoForDashBoard {

	private int mno;
	private String username;
	private String nickName;
	private LocalDate birth;
}
