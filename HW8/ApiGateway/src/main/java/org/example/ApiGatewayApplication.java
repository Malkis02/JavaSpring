package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class,args);
    }

    @Bean
    public RouteLocator customRouteLocator (RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inventory", r -> r.path("/inventory/**")
                        .uri("http://localhost:8080/inventory/")).build();
    }
}