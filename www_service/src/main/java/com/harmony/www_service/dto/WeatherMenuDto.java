package com.harmony.www_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class WeatherMenuDto {
    private double temperature;
    private String description;
    private String icon;
    private List<String> recommendedMenus;
    private List<String> menuImages;

}
