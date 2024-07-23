package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class MenuReqDto {
	private String state;
    private int mrcode;
    private int mno;
    private String username;
    private String menuName;
    private String category;
    private String imgUrl;
    
    
}