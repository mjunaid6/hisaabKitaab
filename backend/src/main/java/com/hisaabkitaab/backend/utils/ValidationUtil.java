package com.hisaabkitaab.backend.utils;

import com.hisaabkitaab.backend.dto.UserDto;

public class ValidationUtil {
    public static boolean isUserNameValid(String username) {
        return username != null && username.length() > 3;
    }

    public static boolean isEmailValid(String email) {
        int idx1 = email.indexOf('@');
        int idx2 = email.indexOf('.');
        return idx1 > 0 && idx2 > 0 && idx1 < idx2;
    }

    public static boolean isPasswordValid(String password) {
        if(password == null || password.length() < 6) return false;
        return !password.contains("@") && 
            !password.contains("#") &&
            !password.contains("$") && 
            !password.contains("&") &&
            !password.contains("!");
    }

    public static boolean isUserValid(UserDto userDto) {
        return isEmailValid(userDto.getEmail()) && 
                isPasswordValid(userDto.getPassword()) &&
                isUserNameValid(userDto.getUserName());
    }
}
