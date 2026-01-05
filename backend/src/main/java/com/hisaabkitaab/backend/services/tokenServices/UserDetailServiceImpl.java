package com.hisaabkitaab.backend.services.tokenServices;

import java.util.HashSet;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hisaabkitaab.backend.dto.UserDto;
import com.hisaabkitaab.backend.entities.User;
import com.hisaabkitaab.backend.repositories.UserRepository;
import com.hisaabkitaab.backend.utils.ValidationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return new UserDetailService(user);
    }

    public boolean signup(UserDto userDto) {
        if(!ValidationUtil.isUserValid(userDto)) return false;

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(checkIfuserAlreadyExists(userDto) != null) return false;

        String userId = UUID.randomUUID().toString();
        userRepository.save(new User(userId, userDto.getUserName(), userDto.getPassword(), userDto.getPassword(), new HashSet<>()));
        return true;
    }


    public User checkIfuserAlreadyExists(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail());
    }

}

