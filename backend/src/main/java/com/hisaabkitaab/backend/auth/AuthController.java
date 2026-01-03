package com.hisaabkitaab.backend.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hisaabkitaab.backend.dto.JwtAuthResponseDto;
import com.hisaabkitaab.backend.dto.UserDto;
import com.hisaabkitaab.backend.services.tokenServices.JWTService;
import com.hisaabkitaab.backend.services.tokenServices.RefreshTokenService;
import com.hisaabkitaab.backend.services.tokenServices.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailServiceImpl userDetailService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponseDto> signUp(@RequestBody UserDto userDto) {

        boolean isUserSignedUp = userDetailService.signup(userDto);
        if (!isUserSignedUp) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        String refreshToken = refreshTokenService
                .createRefreshToken(userDto.getEmail())
                .getToken();

        String accessToken = jwtService.createToken(userDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new JwtAuthResponseDto(accessToken, refreshToken));
    }
}
