package com.hisaabkitaab.backend.auth.service;

import java.util.HashSet;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hisaabkitaab.backend.dto.UserDto;
import com.hisaabkitaab.backend.entities.RefreshToken;
import com.hisaabkitaab.backend.entities.User;
import com.hisaabkitaab.backend.repositories.UserRepository;
import com.hisaabkitaab.backend.utils.ValidationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RefreshTokenService refreshTokenService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return new UserDetailService(user);
    }

    public String signup(UserDto userDto) {
        if(!ValidationUtil.isUserValid(userDto)) return null;

        if(checkIfuserAlreadyExists(userDto) != null) return null;

        User user = new User(UUID.randomUUID().toString(), 
                             userDto.getUsername(), 
                             userDto.getEmail(), 
                             passwordEncoder.encode(userDto.getPassword()), 
                             new HashSet<>(), 
                             null);
                            
        userRepository.save(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDto.getEmail());
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return refreshToken.getToken();
    }


    public User checkIfuserAlreadyExists(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail());
    }

    public String getRefreshToken(String email) {
        return userRepository.findByEmail(email).getRefreshToken().getToken();
    }

}

