package com.lemur.apigateway;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApiGatewayConfig {

    private final AuthFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/api/auth/**").uri("lb://USER"))
                .route("user", r -> r.path("/api/users/**").filters(f -> f.filter(authFilter)).uri("lb://USER"))
                .route("service1", r -> r.path("/api/service1/**").filters(f -> f.filter(authFilter)).uri("lb://SERVICE1"))
                .route("service2", r -> r.path("/api/service2/**").filters(f -> f.filter(authFilter)).uri("lb://SERVICE2"))
                .build();
    }
}
