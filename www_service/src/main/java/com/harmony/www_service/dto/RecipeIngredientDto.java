package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class RecipeIngredientDto {
    private int ricode;
    private int rcode;
    private int icode;
    private int amount;
    private String name;
    private String type;
}
