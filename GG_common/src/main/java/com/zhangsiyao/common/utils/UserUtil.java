package com.zhangsiyao.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

public class UserUtil {
    @SneakyThrows
    public static String getUsernameByToken(StringRedisTemplate redisTemplate, String token){
        ObjectMapper objectMapper=new ObjectMapper();
        String username=objectMapper.readValue(redisTemplate.opsForValue().get(token),String.class);
        return username;
    }



}
