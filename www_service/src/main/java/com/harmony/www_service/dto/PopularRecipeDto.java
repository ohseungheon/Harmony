package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class PopularRecipeDto {
	
	private int rcode;
	private String recipeName;
	private String menuName;
	private String imgurl;
	private int recommendCnt;
	

}
