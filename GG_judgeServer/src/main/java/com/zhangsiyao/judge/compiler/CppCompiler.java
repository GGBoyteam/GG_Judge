package com.zhangsiyao.judge.compiler;

import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.judge.compiler.annotation.Compiler;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author iii
 */
@SuppressWarnings("allap")
@Service
@Compiler(language = Language.CPP,version = {"11"})
public class CppCompiler extends AbstractCompiler {

    @Override
    public JudgeParam runParam(String input, Long timeLimit, Long memoryLimit) {
        JudgeParam judgeParam=new JudgeParam();
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Collections.singletonList("origin"));
        if(input!=null&&input.length()>0){
            cmd.getFiles().addFirst(new JudgeParam.MemoryFile(input));
        }
        //设置编译时间限制为10s
        cmd.setCpuLimit(timeLimit);
        cmd.setClockLimit(2*timeLimit);
        cmd.setMemoryLimit(memoryLimit);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("origin",new JudgeParam.PreparedFile(this.fileId));
        judgeParam.getCmd().add(cmd);
        return judgeParam;
    }

    @Override
    public JudgeParam compileParam(String code, String version) {
        JudgeParam judgeParam=new JudgeParam();
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("g++","-O2","-std=c++"+ version,"origin.cpp","-o","origin"));
        //设置编译时间限制为600s
        cmd.setCpuLimit(36000000000L);
        //设置编译内存限制为256MB
        cmd.setMemoryLimit(268435456L);
        cmd.setProcLimit(50);
        cmd.setStrictMemoryLimit(false);
        cmd.getCopyIn().put("origin.cpp",new JudgeParam.MemoryFile(code));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Collections.singletonList("origin"));
        judgeParam.getCmd().add(cmd);
        return judgeParam;
    }

}
