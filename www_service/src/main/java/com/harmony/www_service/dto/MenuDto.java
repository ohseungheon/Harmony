package com.harmony.www_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private int mcode;
    private String menuName;
    private String category;
    private String imgurl;
    private int VIEW;
    private String type;
    private String menuTemperature;
    private String menuWeather;
}