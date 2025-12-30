package com.hisaabkitaab.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTService {
    
    @Value("${jwt.secret}")
    public static final String secretKey = "9uX1Q7rVn3sYB4yE0Hq6mA2cF5xZbJdT8WcRkPpL0Qo";

    public static void main(String[] args) {
        System.out.println(secretKey);
    }
}
