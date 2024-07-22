package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private int rcode;
    private int mcode;
    private int mno;
    private String recipeName;
    private String introduce;
    private String url;
    private String category;
    private int portions;
    private int view;
    private String menuName;
    private String theme;
}
