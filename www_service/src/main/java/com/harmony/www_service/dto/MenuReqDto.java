package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class MenuReqDto {
    private int mno;
    private String menuName;
    private String category;
    private String imgUrl;
}
