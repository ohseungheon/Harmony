package com.harmony.www_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final WebClient webClient;

    public ProxyController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/rural-experience-activities/**")
    public Mono<String> proxyRequest(HttpServletRequest request) {
        String uri = "http://211.237.50.150:7080" + request.getRequestURI().replace("/proxy/rural-experience-activities", "");
        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }
}
