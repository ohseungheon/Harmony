package com.harmony.www_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto2 {
    private int mcode;
    private String imgurl;
    private int VIEW;
    private String type;
    private String menuTemperature;
    private String menuWeather;
    private int rcode;
    private int mno;
    private String recipeName;
    private String introduce;
    private String url;
    private String category;
    private int portions;
    private int view;
    private String menuName;
    private String theme;
    private String lastCookingImg;
    private int lackNum;
    private List<String> lackIngredient;
}