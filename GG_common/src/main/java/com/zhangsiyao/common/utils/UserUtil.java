package com.zhangsiyao.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

public class UserUtil {
    @SneakyThrows
    public static String getUsernameByToken(StringRedisTemplate redisTemplate, String token){
        ObjectMapper objectMapper=new ObjectMapper();
        UserLogin userLogin=objectMapper.readValue(redisTemplate.opsForValue().get(token),UserLogin.class);
        return userLogin.getUsername();
    }
}
