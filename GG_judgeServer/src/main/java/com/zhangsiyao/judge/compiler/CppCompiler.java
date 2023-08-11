package com.zhangsiyao.judge.compiler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.*;

/**
 * @author iii
 */
@SuppressWarnings("allap")
@Service
public class CppCompiler implements Serializable {


    private final String judgeServerUrl="http://172.16.0.49:5050";

    private String fileId=null;

    private final Queue<String> fileIds=new LinkedList<>();

    private final RestTemplate restTemplate=new RestTemplate();


    private final ObjectMapper objectMapper=new ObjectMapper();



    @SneakyThrows
    public JudgeResult compile(String content, Integer version) {
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        System.out.println(judgeServerUrl);
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("g++","-O2","-std=c++"+ version,"origin.cpp","-o","origin"));
        //设置编译时间限制为600s
        cmd.setCpuLimit(36000000000L);
        //设置编译内存限制为256MB
        cmd.setMemoryLimit(268435456L);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("origin.cpp",new JudgeParam.MemoryFile(content));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Collections.singletonList("origin"));
        judgeParam.getCmd().add(cmd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(judgeParam), String.class);
        List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        JudgeResult judgeResult=judgeResultList.get(0);
        if(judgeResult.getStatus()== JudgeResult.Status.Accepted){
            fileId=judgeResult.getFileIds().get("origin");
            fileIds.addAll(judgeResult.getFileIds().values());
        }
        return judgeResult;
    }

    @SneakyThrows
    public JudgeResult run(long timeLimit, long memoryLimit, String input){
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Collections.singletonList("origin"));
        cmd.getFiles().add(new JudgeParam.MemoryFile(input));
        //设置编译时间限制为10s
        cmd.setCpuLimit(timeLimit);
        cmd.setClockLimit(2*timeLimit);
        cmd.setMemoryLimit(memoryLimit);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("origin",new JudgeParam.PreparedFile(fileId));
        judgeParam.getCmd().add(cmd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(judgeParam), String.class);
        List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        return judgeResultList.get(0);
    }

    public void removeFiles(){
        String url=judgeServerUrl+"/file/";
        while (!fileIds.isEmpty()){
            restTemplate.delete(url+fileIds.poll());
        }
    }

}
