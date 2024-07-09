package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class RecipeDto {
    private int rcode;
    private int mcode;
    private String recipeName;
    private String introduce;
    private String url;
    private String category;
    private int portions;
    private int view;
}
