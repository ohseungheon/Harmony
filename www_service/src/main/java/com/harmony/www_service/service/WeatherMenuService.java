package com.harmony.www_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.harmony.www_service.dto.WeatherMenuDto;
import java.util.*;

@Service
public class WeatherMenuService {
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid=5ec2a8343328554022efe9f92a7fdfa3&units=metric";

    private static final Map<String, String> weatherMapping = Map.ofEntries(
        Map.entry("clear sky", "맑음"),
        Map.entry("few clouds", "맑음"),
        Map.entry("scattered clouds", "맑음"),
        Map.entry("broken clouds", "흐림"),
        Map.entry("overcast clouds", "흐림"),
        Map.entry("light rain", "비"),
        Map.entry("moderate rain", "비"),
        Map.entry("heavy intensity rain", "비"),
        Map.entry("shower rain", "비"),
        Map.entry("rain", "비"),
        Map.entry("thunderstorm", "비"),
        Map.entry("snow", "눈"),
        Map.entry("mist", "안개"),
        Map.entry("fog", "안개"),
        Map.entry("haze", "안개"),
        Map.entry("smoke", "안개"),
        Map.entry("dust", "안개"),
        Map.entry("sand", "안개"),
        Map.entry("ash", "안개"),
        Map.entry("squalls", "비"),
        Map.entry("tornado", "비")
    );


    private static final Map<String, String> weatherIcons = Map.of(
        "맑음", "☀️",
        "흐림", "☁️",
        "비", "🌧️",
        "눈", "❄️",
        "안개", "🌫️"
    );

    public WeatherMenuDto getWeatherMenuRecommendation() {
        WeatherMenuDto weatherMenuDto = new WeatherMenuDto();
        
        RestTemplate restTemplate = new RestTemplate();
        String city = "Busan";
        String url = API_URL.replace("{city}", city);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        List<Map<String, Object>> weather = (List<Map<String, Object>>) response.get("weather");
        
        double temp = ((Number) main.get("temp")).doubleValue();
        String description = (String) weather.get(0).get("description");
        String simplifiedDescription = weatherMapping.getOrDefault(description, "맑음");
        String icon = weatherIcons.getOrDefault(simplifiedDescription, "☀️");

        weatherMenuDto.setTemperature(temp);
        weatherMenuDto.setDescription(simplifiedDescription);
        weatherMenuDto.setIcon(icon);
        weatherMenuDto.setRecommendedMenus(getRecommendedMenus(temp));

        return weatherMenuDto;
    }

    private List<String> getRecommendedMenus(double temperature) {
        if (temperature < 26) {
            return Arrays.asList("라멘", "돈카츠", "짬뽕", "딤섬", "소바");
        } else if (temperature >= 26 && temperature < 27) {
            return Arrays.asList("김치찌개", "소바", "스테이크", "삼계탕", "라멘");
        } else {
            return Arrays.asList("하이라이스", "와플", "동태찌개", "호박죽", "소바");
        }
    }
}