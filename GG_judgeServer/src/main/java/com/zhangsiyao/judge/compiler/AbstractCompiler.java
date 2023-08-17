package com.zhangsiyao.judge.compiler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.judge.exception.CodeCompileException;
import com.zhangsiyao.judge.exception.JudgeRequestException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractCompiler implements ICompiler{

    private final String judgeServerUrl="http://172.16.0.38:5050";

    protected String fileId=null;

    private final Queue<String> fileIds=new LinkedList<>();

    private final RestTemplate restTemplate=new RestTemplate();

    private final ObjectMapper objectMapper=new ObjectMapper();


    @SneakyThrows
    @Override
    public final JudgeResult.Status compile(String code, String version) {
        String url=judgeServerUrl+"/run";
        JudgeParam compileParam=compileParam(code,version);
        JudgeResult judgeResult;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(compileParam), String.class);
            List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {});
            judgeResult=judgeResultList.get(0);
        } catch (Exception e) {
            throw new JudgeRequestException();
        }
        if(judgeResult.getStatus()== JudgeResult.Status.Accepted){
            fileId=judgeResult.getFileIds().get("origin");
            fileIds.addAll(judgeResult.getFileIds().values());
        }
        return judgeResult.getStatus();
    }

    @Override
    @SneakyThrows
    public final JudgeResult run(String input, Long timeLimit, Long memoryLimit) {
        String url=judgeServerUrl+"/run";
        JudgeParam runParam=runParam(input,timeLimit,memoryLimit);
        JudgeResult judgeResult;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(runParam), String.class);
            List<JudgeResult> judgeResultList = objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {});
            judgeResult=judgeResultList.get(0);
        } catch (Exception e) {
            throw new JudgeRequestException();
        }
        return judgeResult;
    }



    @Override
    public final void removeFiles() {
        String url=judgeServerUrl+"/file/";
        while (!fileIds.isEmpty()){
            restTemplate.delete(url+fileIds.poll());
        }
    }
}
