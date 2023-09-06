package com.zhangsiyao.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.zhangsiyao")
@EnableFeignClients(basePackages = "com.zhangsiyao")
@SpringBootApplication
public class GgAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(GgAuthApplication.class, args);
    }

}
