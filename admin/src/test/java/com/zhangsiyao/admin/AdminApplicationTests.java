package com.zhangsiyao.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.admin.service.CompileParamService;
import com.zhangsiyao.admin.service.CompilerService;
import com.zhangsiyao.admin.service.ExampleService;
import com.zhangsiyao.admin.service.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AdminApplicationTests {


    @Autowired
    ProblemService problemService;

    @Autowired
    CompilerService compilerService;

    @Autowired
    CompileParamService compileParamService;

    @Autowired
    ExampleService exampleService;


    @Test
    void contextLoads() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(problemService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compilerService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compileParamService.query().list()));
        System.out.println(objectMapper.writeValueAsString(exampleService.query().list()));
    }

}
