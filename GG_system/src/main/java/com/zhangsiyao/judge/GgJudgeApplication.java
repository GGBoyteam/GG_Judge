package com.zhangsiyao.judge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.zhangsiyao.common")
@ComponentScan(value = "com.zhangsiyao")
@SpringBootApplication
public class GgJudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgJudgeApplication.class, args);
    }

}
