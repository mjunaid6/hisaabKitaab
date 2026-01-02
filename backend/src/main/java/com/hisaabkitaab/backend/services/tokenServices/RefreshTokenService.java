package com.hisaabkitaab.backend.services.tokenServices;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisaabkitaab.backend.entities.RefreshToken;
import com.hisaabkitaab.backend.entities.User;
import com.hisaabkitaab.backend.repositories.RefreshTokenRepository;
import com.hisaabkitaab.backend.repositories.UserRepository;

@Service
public class RefreshTokenService {
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private final int expirationTime = 1000 * 60 * 60 * 24 * 7;

    public RefreshToken createRefreshToken(String email) {
        User user = userRepository.findByEmail(email);
        RefreshToken refreshToken = RefreshToken.builder()
                                                .user(user)
                                                .token(UUID.randomUUID().toString())
                                                .expiry(Instant.now().plusMillis(expirationTime))
                                                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiry().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + " refresh token is expired");
        }
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

}
