package com.hisaabkitaab.backend.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.hisaabkitaab.backend.entities.RefreshToken;

import ch.qos.logback.core.subst.Token;

@Service
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTService {
    
    @Value("${jwt.secret}")
    public static final String secretKey = "9uX1Q7rVn3sYB4yE0Hq6mA2cF5xZbJdT8WcRkPpL0Qo";

    private static final Instant expirationTime = Instant.ofEpochSecond(3600000);

    private boolean isValidToken(RefreshToken token){
        return token.getExpiry().isBefore(expirationTime);
    }

    public static void main(String[] args) {
        System.out.println(secretKey);
        System.out.println(expirationTime);
    }
}
