package com.zhangsiyao.gateway;

import com.zhangsiyao.common.component.JwtComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayApplicationTests {

    @Autowired
    JwtComponent jwtComponent;

    @Test
    void contextLoads() throws Exception {
        String token= jwtComponent.createToken("渣打我的");
        System.out.println(token);
        System.out.println(jwtComponent.parseJWT(token).getSubject());
    }

}
