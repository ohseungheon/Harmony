package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class Menu_favoriteDto_by {
	private int fmcode; //좋아요코드
	private int mno;
	private int mcode; //메뉴코드
	
	private String menuName;
	private String imgUrl;
}
