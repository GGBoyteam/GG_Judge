package com.zhangsiyao.judge.service.impl;

import com.zhangsiyao.common.entity.judge.vo.CodeCompileVo;
import com.zhangsiyao.judge.compiler.CppCompiler;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompilerServiceImpl implements ICompilerService {

    @Autowired
    CppCompiler cppCompiler;

    @Override
    public JudgeResult compile(CodeCompileVo codeCompileVo) {
        if("c++".equals(codeCompileVo.getLanguage())){
            JudgeResult result = cppCompiler.compile(codeCompileVo.getCode(), codeCompileVo.getVersion());
            cppCompiler.removeFiles();
            return result;
        }
        return null;
    }
}
