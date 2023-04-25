package com.lemur.apigateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtil {

    @Value("secret")
    private String secret;

    public JwtUtil() {
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean validateTokenWithRoles(String token, List<String> roles) {
        String role = (String) getAllClaimsFromToken(token).get("role");
        return !isTokenExpired(token) && roles.contains(role);
    }

}
