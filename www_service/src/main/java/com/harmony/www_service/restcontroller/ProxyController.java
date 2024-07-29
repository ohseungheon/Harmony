// package com.harmony.www_service.restcontroller;

// import org.springframework.core.io.buffer.DataBufferLimitException;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.reactive.function.client.WebClient;

// import jakarta.servlet.http.HttpServletRequest;
// import reactor.core.publisher.Mono;

// @RestController
// @RequestMapping("/proxy")
// public class ProxyController {

//     private String serviceKey = "69bb1f84-ae0c-4352-a558-023aaa11e1ae";
//     private String serviceID = "4619";

//     private final WebClient webClient;

//     public ProxyController(WebClient.Builder webClientBuilder) {
//         this.webClient = webClientBuilder.build();
//     }

//     @GetMapping("/rural-experience-activities/**")
//     public Mono<String> proxyRequest(HttpServletRequest request) {
//         String uri = "http://211.237.50.150:7080"
//                 + request.getRequestURI().replace("/proxy/rural-experience-activities", "");

//         return webClient.get()
//                 .uri(uri)
//                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .retrieve()
//                 .bodyToMono(String.class);
//     }

//     @GetMapping("/retail-product-price/**")
//     public Mono<String> retail_product_price(HttpServletRequest request) {
//         String uri = "https://www.kamis.or.kr/service/price/xml.do"
//                 + "?action=periodProductList&p_productclscode=01&";
//         uri += request.getRequestURI().replace("/proxy/retail-product-price/", "");
//         uri += "&p_cert_key=" + serviceKey
//                 + "&p_cert_id=" + serviceID
//                 + "&p_returntype=json";

//         System.out.println(uri);

//         return webClient.get()
//                 .uri(uri)
//                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .retrieve()
//                 .bodyToFlux(String.class)
//                 .reduce("", (acc, chunk) -> acc + chunk)
//                 .onErrorResume(DataBufferLimitException.class, ex -> {
//                     System.err.println("DataBufferLimitException: " + ex.getMessage());
//                     return Mono.just("Error: Data buffer limit exceeded");
//                 });
//     }

//     @GetMapping("/retail-price/**")
//     public Mono<String> retail_price(HttpServletRequest request) {
//         String uri = "https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList&p_product_cls_code=01&"
//                 + request.getRequestURI().replace("/proxy/retail-price/", "");
//         uri += "&p_cert_key=" + serviceKey
//                 + "&p_cert_id=" + serviceID
//                 + "&p_returntype=json";

//         System.out.println(uri);

//         return webClient.get()
//                 .uri(uri)
//                 .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                 .retrieve()
//                 .bodyToFlux(String.class)
//                 .reduce("", (acc, chunk) -> acc + chunk)
//                 .onErrorResume(DataBufferLimitException.class, ex -> {
//                     System.err.println("DataBufferLimitException: " + ex.getMessage());
//                     return Mono.just("Error: Data buffer limit exceeded");
//                 });
//     }
// }
