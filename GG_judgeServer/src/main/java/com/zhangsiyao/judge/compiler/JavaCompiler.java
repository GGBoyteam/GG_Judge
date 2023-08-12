package com.zhangsiyao.judge.compiler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.judge.exception.CodeCompileException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("all")
@Service
public class JavaCompiler implements ICompiler{

    private final String judgeServerUrl="http://172.16.0.49:5050";

    private String fileId=null;

    private final Set<String> fileIds=new HashSet<String>();

    private final RestTemplate restTemplate=new RestTemplate();


    private final ObjectMapper objectMapper=new ObjectMapper();


    public JavaCompiler() {
    }

    @Override
    @SneakyThrows
    public JudgeResult.Status compile(String content, Integer version) throws CodeCompileException {
        System.out.println(content);
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("/bin/bash", "-c", "javac Main.java && jar cvf Main.jar *.class"));
        //设置编译时间限制为600s
        cmd.setCpuLimit(36000000000L);
        //设置编译内存限制为256MB
        cmd.setMemoryLimit(268435456L);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("Main.java",new JudgeParam.MemoryFile(content));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Arrays.asList("Main.jar"));
        judgeParam.getCmd().add(cmd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(judgeParam), String.class);
        List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        JudgeResult judgeResult=judgeResultList.get(0);
        fileId=judgeResult.getFileIds().get("Main.jar");
        fileIds.addAll(judgeResult.getFileIds().values());
        System.out.println(judgeResult);
        return judgeResult.getStatus();
    }

    @SneakyThrows
    @Override
    public JudgeResult run(String input,Long timeLimit, Long memoryLimit){
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("/usr/bin/java","-cp","Main.jar","Main"));
        cmd.getFiles().add(new JudgeParam.MemoryFile(input));
        cmd.setCpuLimit(timeLimit);
        cmd.setClockLimit(2*timeLimit);
        cmd.setMemoryLimit(memoryLimit);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("Main.jar",new JudgeParam.PreparedFile(fileId));
        judgeParam.getCmd().add(cmd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(judgeParam), String.class);
        List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        System.out.println(judgeResultList.get(0));
        return judgeResultList.get(0);
    }

    @Override
    public void removeFiles(){
        String url=judgeServerUrl+"/file/";
        for(String file:fileIds){
            restTemplate.delete(url+file);
        }
    }
}
