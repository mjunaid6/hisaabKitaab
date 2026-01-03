package com.hisaabkitaab.backend.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hisaabkitaab.backend.dto.AuthRequestDto;
import com.hisaabkitaab.backend.dto.JwtAuthResponseDto;
import com.hisaabkitaab.backend.dto.RefreshTokenRequestDto;
import com.hisaabkitaab.backend.entities.RefreshToken;
import com.hisaabkitaab.backend.services.tokenServices.JWTService;
import com.hisaabkitaab.backend.services.tokenServices.RefreshTokenService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class TokenController {
    private RefreshTokenService refreshTokenService;
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        
        if(!authentication.isAuthenticated()) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            
        return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(JwtAuthResponseDto.builder()
                                                    .accessToken(jwtService.createToken(authRequestDto.getEmail()))
                                                    .refreshToken(refreshTokenService.createRefreshToken(authRequestDto.getEmail()).getToken())
                                                    .build());
    }

    @PostMapping("/refreshToken")
    public JwtAuthResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws RuntimeException {
        Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(refreshTokenRequestDto.getRefreshToken());
        if(refreshToken == null || refreshTokenService.verifyExpiration(refreshToken.get())) throw new RuntimeException("Refresh Token not found");
        return JwtAuthResponseDto.builder()
                                    .accessToken(jwtService.createToken(refreshToken.get().getUser().getEmail()))
                                    .refreshToken(refreshToken.get().getToken())
                                    .build();

    }
}
