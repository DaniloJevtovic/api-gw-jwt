package com.lemur.apigateway;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class RoleAuthGatewayFilterFactory extends
        AbstractGatewayFilterFactory<RoleAuthGatewayFilterFactory.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public RoleAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
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
                    boolean valid = jwtUtil.validateTokenWithRoles(token, config.getRoles());

                    if (!valid) {
                        response.setStatusCode(HttpStatus.FORBIDDEN);
                        return response.setComplete();
                    }

                } catch (Exception e) {
                    log.error(e.getMessage());
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                Claims claims = jwtUtil.getAllClaimsFromToken(token);
                exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
            }

            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private List<String> roles;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("roles");
    }
}
