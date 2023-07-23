package com.zhangsiyao.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.admin.service.CompileParamService;
import com.zhangsiyao.admin.service.CompilerService;
import com.zhangsiyao.admin.service.ExampleService;
import com.zhangsiyao.admin.service.ProblemService;
import com.zhangsiyao.common.compiler.CppCompiler;
import com.zhangsiyao.common.compiler.JavaCompiler;
import com.zhangsiyao.common.result.JudgeResult;
import com.zhangsiyao.common.send.JudgeParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;


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
    void java(){
        JavaCompiler javaCompiler=new JavaCompiler("http://222.187.223.125:35811",11);
        try {
            javaCompiler.compile("public class Main {\n" +
                    "\n" +
                    "    public static void main(String[] args) {\n" +
                    "        System.out.println(\"aaa\");\n" +
                    "    }\n" +
                    "\n" +
                    "\n" +
                    "}");
            javaCompiler.run(1000000000,10000000000L,"");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            javaCompiler.removeAll();
        }

    }

    @Test
    void a() throws JsonProcessingException {
        CppCompiler compiler=new CppCompiler("http://222.187.223.125:35811",20);
        try {
            compiler.compile("#include <iostream>\nusing namespace std;\nint main() {\nint a, b;\ncin >> a >> b;\ncout << a + b << endl;\n}");
            JudgeResult run = compiler.run(10000000000L, 100000000L, "1 1\n");
            System.out.println(run);
        }finally {
            compiler.removeAll();
        }

    }


    @Test
    void contextLoads() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(problemService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compilerService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compileParamService.query().list()));
        System.out.println(objectMapper.writeValueAsString(exampleService.query().list()));
    }


    String url="http://222.187.223.125:35811/run";
    @Test
    void Post() throws JsonProcessingException {
        JudgeParam param=new JudgeParam();
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("/usr/bin/g++","a.cc","-o","a"));
        cmd.setEnv(Arrays.asList("PATH=/usr/bin:/bin"));
        cmd.setCpuLimit(10000000000L);
        cmd.setMemoryLimit(104857600L);
        cmd.setProcLimit(50);
        cmd.getCopyIn().put("a.cc",new JudgeParam.MemoryFile("#include <iostream>\nusing namespace std;\nint main() {\nint a, b;\ncin >> a >> b;\ncout << a + b << endl;\n}"));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Arrays.asList("a"));
        param.getCmd().add(cmd);
        ObjectMapper objectMapper=new ObjectMapper();
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(param),String.class);
        objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        System.out.println(response.getBody());

    }

}
