package com.zhangsiyao.common.compiler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.result.JudgeResult;
import com.zhangsiyao.common.send.JudgeParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class CppCompiler {

    private final String judgeServerUrl;

    private int cppVersion=11;

    private String fileId=null;

    private final Set<String> fileIds=new HashSet<String>();

    private final RestTemplate restTemplate=new RestTemplate();


    private final ObjectMapper objectMapper=new ObjectMapper();


    public CppCompiler(String judgeServerUrl,int cppVersion) {
        this.judgeServerUrl = judgeServerUrl;
        this.cppVersion=cppVersion;
    }

    public JudgeResult compile(String content) throws JsonProcessingException {
        System.out.println(content);
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("g++","-std=c++"+ cppVersion,"origin.cpp","-O2","origin"));
        cmd.setFiles(Arrays.<Object>asList(
                new JudgeParam.Collector("stdout",10240,false),
                new JudgeParam.Collector("stderr",10240,false)
        ));
        //设置编译时间限制为600s
        cmd.setCpuLimit(36000000000L);
        //设置编译内存限制为256MB
        cmd.setMemoryLimit(268435456L);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("origin.cpp",new JudgeParam.MemoryFile(content));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Arrays.asList("origin.cpp","origin"));
        judgeParam.getCmd().add(cmd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(judgeParam), String.class);
        List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        JudgeResult judgeResult=judgeResultList.get(0);
        fileId=judgeResult.getFileIds().get("origin");
        fileIds.addAll(judgeResult.getFileIds().values());
        System.out.println(judgeResult);
        return judgeResult;
    }

    public JudgeResult run(long timeLimit,long memoryLimit,String input) throws JsonProcessingException {
        JudgeParam judgeParam=new JudgeParam();
        String url=judgeServerUrl+"/run";
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Collections.singletonList("origin"));
        cmd.setFiles(Arrays.<Object>asList(
                new JudgeParam.MemoryFile(input),
                new JudgeParam.Collector("stdout",10240,false),
                new JudgeParam.Collector("stderr",10240,false)
        ));
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

    public void removeAll(){
        String url=judgeServerUrl+"/file/";
        for(String file:fileIds){
            restTemplate.delete(url+file);
        }
    }

}
