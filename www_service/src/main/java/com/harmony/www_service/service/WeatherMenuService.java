package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harmony.www_service.dao.MenuDao;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.WeatherMenuDto;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherMenuService {
    @Autowired
    private MenuDao menuDao;

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

        // 하드코딩 테스트용
        double temp = 17.0; 
        String description = "clear sky";

        // RestTemplate restTemplate = new RestTemplate();
        // String city = "Busan";
        // String url = API_URL.replace("{city}", city);

        // Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Map<String, Object> main = (Map<String, Object>) response.get("main");
        // List<Map<String, Object>> weather = (List<Map<String, Object>>) response.get("weather");

        // double temp = ((Number) main.get("temp")).doubleValue();
        // String description = (String) weather.get(0).get("description");
        String simplifiedDescription = weatherMapping.getOrDefault(description, "맑음");
        String icon = weatherIcons.getOrDefault(simplifiedDescription, "☀️");

        weatherMenuDto.setTemperature(temp);
        weatherMenuDto.setDescription(simplifiedDescription);
        weatherMenuDto.setIcon(icon);

        List<MenuDto> recommendedMenus = getRecommendedMenus(temp, simplifiedDescription);
        weatherMenuDto.setRecommendedMenus(recommendedMenus);

        return weatherMenuDto;
    }

    private List<MenuDto> getRecommendedMenus(double temperature, String weather) {
        // 날씨에 맞는 메뉴를 가져옴
        Set<MenuDto> weatherMenus = getWeatherMenus(weather);

        // 현재 온도에 맞는 메뉴를 가져옴
        String primaryCategory = getTemperatureCategory(temperature);
        Set<MenuDto> primaryTempMenus = new HashSet<>(menuDao.findByMenuTemperature(primaryCategory));

        // 서브 온도 카테고리 결정
        String secondaryCategory = getSecondaryTemperatureCategory(temperature, weather);
        Set<MenuDto> secondaryTempMenus = new HashSet<>(menuDao.findByMenuTemperature(secondaryCategory));

        // 날씨와 온도에 맞는 메뉴를 필터링
        List<MenuDto> currentTempMenus = weatherMenus.stream()
            .filter(menu -> primaryTempMenus.contains(menu))
            .collect(Collectors.toList());

        List<MenuDto> secondaryTempMenusList = weatherMenus.stream()
            .filter(menu -> secondaryTempMenus.contains(menu))
            .collect(Collectors.toList());

        List<MenuDto> recommendedMenus = new ArrayList<>();

        // 현재 온도에 맞는 메뉴 3개 추천
        addRandomMenus(recommendedMenus, currentTempMenus, 3);

        // 서브 온도에 맞는 메뉴 2개 추천
        addRandomMenus(recommendedMenus, secondaryTempMenusList, 2);

        // 부족한 메뉴를 추가
        if (recommendedMenus.size() < 5) {
            Set<MenuDto> remainingWeatherMenus = new HashSet<>(weatherMenus);
            remainingWeatherMenus.removeAll(recommendedMenus);
            addRandomMenus(recommendedMenus, new ArrayList<>(remainingWeatherMenus), 5 - recommendedMenus.size());
        }

        // 그래도 메뉴가 부족할 경우, 랜덤 메뉴를 추가
        if (recommendedMenus.size() < 5) {
            List<MenuDto> allMenus = menuDao.findAll();
            allMenus.removeIf(menu -> "기타".equals(menu.getCategory()));
            addRandomMenus(recommendedMenus, allMenus, 5 - recommendedMenus.size());
        }

        Collections.shuffle(recommendedMenus);
        return recommendedMenus.subList(0, Math.min(recommendedMenus.size(), 5));
    }

    private Set<MenuDto> getWeatherMenus(String weather) {
        switch (weather) {
            case "맑음":
                return new HashSet<>(menuDao.findByMenuWeather("sunny"));
            case "비":
                return new HashSet<>(menuDao.findByMenuWeather("rain"));
            case "눈":
                return new HashSet<>(menuDao.findByMenuWeather("snow"));
            default:
                return new HashSet<>(menuDao.findByMenuWeather("sunny"));
        }
    }

    private void addRandomMenus(List<MenuDto> targetList, List<MenuDto> sourceList, int count) {
        List<MenuDto> newMenus = sourceList.stream()
                .filter(menu -> !targetList.contains(menu))
                .collect(Collectors.toList());
        Collections.shuffle(newMenus);
        targetList.addAll(newMenus.stream().limit(count).collect(Collectors.toList()));
    }

    private String getTemperatureCategory(double temperature) {
        if (temperature < 15) return "hot";  
        else if (temperature < 25) return "average";  
        else return "ice";  
    }

    private String getSecondaryTemperatureCategory(double temperature, String weather) {
        String primaryCategory = getTemperatureCategory(temperature);

        if (weather.equals("비") || weather.equals("눈")) {
            if (primaryCategory.equals("ice")) return "average";  
            if (primaryCategory.equals("hot")) return "average";  
        }
        if (weather.equals("맑음")) {
            if (primaryCategory.equals("ice")) return "average";
            if (primaryCategory.equals("hot")) return "average";
            if (primaryCategory.equals("average")) return "average";
        }

        return primaryCategory.equals("average") ? "hot" : "average";
    }
}