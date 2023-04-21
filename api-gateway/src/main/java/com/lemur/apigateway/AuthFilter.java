package com.lemur.apigateway;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
@Slf4j
public class AuthFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        final List<String> apiEndpoints = List.of("api/auth/register", "api/auth/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (!request.getHeaders().containsKey("Authorization")) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        if (isApiSecured.test(request)) {
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                log.error(e.getMessage());
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            Claims claims = jwtUtil.getAllClaimsFromToken(token);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
        }

        return chain.filter(exchange);
    }
}
