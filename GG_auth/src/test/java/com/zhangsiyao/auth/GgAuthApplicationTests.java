package com.zhangsiyao.auth;

import com.zhangsiyao.auth.component.Md5Component;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import  org.apache.commons.codec.digest.DigestUtils;

@SpringBootTest
class GgAuthApplicationTests {
    @Autowired
    Md5Component md5Component;
    @Test
    void contextLoads() {
        String hex = DigestUtils.md5Hex(md5Component.getSALT()+"张思耀"+md5Component.getSALT());
        System.out.println(hex);
    }

}
