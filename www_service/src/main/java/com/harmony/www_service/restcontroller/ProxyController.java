package com.harmony.www_service.restcontroller;

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

    @GetMapping("/retail-price/**")
    public Mono<String> retail_price(HttpServletRequest request) {
        // String uri = "http://www.kamis.or.kr/?p_cert_key=69bb1f84-ae0c-4352-a558-023aaa11e1ae&p_cert_id=4619&p_returntype=json&p_productclscode=01&" + request.getRequestURI().replace("/proxy/retail-price/", "");
        String uri = "https://www.kamis.or.kr/service/price/xml.do?action=periodProductList&p_productclscode=02&";
        uri += request.getRequestURI().replace("/proxy/retail-price/", "");
        uri +=  "&p_cert_key=69bb1f84-ae0c-4352-a558-023aaa11e1ae&p_cert_id=4619&p_returntype=json";

        System.out.println(uri);
        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }
}
