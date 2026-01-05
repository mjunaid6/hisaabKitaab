package com.hisaabkitaab.backend.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisaabkitaab.backend.auth.service.JWTService;
import com.hisaabkitaab.backend.auth.service.RefreshTokenService;
import com.hisaabkitaab.backend.auth.service.UserDetailServiceImpl;
import com.hisaabkitaab.backend.dto.AuthRequestDto;
import com.hisaabkitaab.backend.dto.JwtAuthResponseDto;
import com.hisaabkitaab.backend.dto.RefreshTokenRequestDto;
import com.hisaabkitaab.backend.dto.UserDto;
import com.hisaabkitaab.backend.entities.RefreshToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTService jwtService;
    private final UserDetailServiceImpl userDetailService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponseDto> signUp(@RequestBody UserDto userDto) {

        String refreshToken = userDetailService.signup(userDto);
        if (refreshToken == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(JwtAuthResponseDto
                                                    .builder()
                                                    .accessToken(jwtService.createToken(userDto.getEmail()))
                                                    .refreshToken(refreshToken)
                                                    .build());
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
            
        return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(JwtAuthResponseDto.builder()
                                                    .accessToken(jwtService.createToken(authRequestDto.getEmail()))
                                                    .refreshToken(refreshTokenService.createRefreshToken(authRequestDto.getEmail()).getToken())
                                                    .build());
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws RuntimeException {
        RefreshToken refreshToken = refreshTokenService
            .findByToken(refreshTokenRequestDto.getRefreshToken())
            .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        if(!refreshTokenService.verifyExpiration(refreshToken)) throw new RuntimeException("Refresh Token expired");
        return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(JwtAuthResponseDto.builder()
                                    .accessToken(jwtService.createToken(refreshToken.getUser().getEmail()))
                                    .refreshToken(refreshToken.getToken())
                                    .build());

    }
}
