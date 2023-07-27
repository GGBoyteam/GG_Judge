package com.zhangsiyao.auth;

import com.zhangsiyao.auth.component.Md5Component;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GgAuthApplicationTests {
    @Autowired
    Md5Component md5Component;
    @Test
    void contextLoads() {

    }

}
