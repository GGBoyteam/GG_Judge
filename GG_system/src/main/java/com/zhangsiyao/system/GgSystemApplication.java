package com.zhangsiyao.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.zhangsiyao.common")
@ComponentScan(value = "com.zhangsiyao")
@SpringBootApplication
public class GgSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgSystemApplication.class, args);
    }

}
