package com.hisaabkitaab.backend.utils;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hisaabkitaab.backend.dto.UserDto;

public class UserSerializer implements Serializer<UserDto>{

    @Override
    public byte[] serialize(String topic, UserDto userDto) {
        byte[] result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            result = objectMapper.writeValueAsString(userDto).getBytes();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

