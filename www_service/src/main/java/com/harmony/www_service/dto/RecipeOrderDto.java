package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class RecipeOrderDto {
    private int rocode;
    private int rcode;
    private String orderContent;
    private int orderNum;
    private String cookingImg;
}
