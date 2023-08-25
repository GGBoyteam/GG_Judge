package com.zhangsiyao.judge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.zhangsiyao")
@ComponentScan("com.zhangsiyao")
public class GgJudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgJudgeApplication.class, args);
    }

}
