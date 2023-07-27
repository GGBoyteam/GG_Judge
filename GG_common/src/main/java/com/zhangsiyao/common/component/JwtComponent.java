package com.zhangsiyao.common.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtComponent {

    @Value("${jwt.ttl:100000000}")
    private long TTL;

    @Value("${jwt.secret:testSecret}")
    private String SECRET;


    public String createToken(String body){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        UUID uuid=UUID.randomUUID();
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder()
                .setId(uuid.toString())
                .setIssuedAt(now)
                .setSubject(body)
                .signWith(signatureAlgorithm, generalKey());
        if (TTL >= 0) {
            long expMillis = nowMillis + TTL;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }



    public Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        return Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();
    }

    private  SecretKey generalKey(){
        byte[] encodeKey = Base64.getDecoder().decode(SECRET);
        return new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
    }



}
