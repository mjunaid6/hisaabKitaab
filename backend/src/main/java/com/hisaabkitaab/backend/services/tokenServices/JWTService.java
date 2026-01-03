package com.hisaabkitaab.backend.services.tokenServices;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTService {
    
    // @Value("${jwt.secret}")
    public static final String secretKey = "9uX1Q7rVn3sYB4yE0Hq6mA2cF5xZbJdT8WcRkPpL0Qo";

    private static final int expirationTime = 1000 * 60;

    public String createToken(String email) {
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetailServices) {
        final String email = extractEmail(token);
        return (email.equals(userDetailServices.getUsername()) && !isTokenExpired(token));
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims:: getSubject);
    }

    private Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver ) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

}
