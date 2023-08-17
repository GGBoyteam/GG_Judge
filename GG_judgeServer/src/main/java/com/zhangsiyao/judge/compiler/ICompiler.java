package com.zhangsiyao.judge.compiler;

import com.zhangsiyao.judge.exception.CodeCompileException;
import com.zhangsiyao.judge.exception.CodeRunException;

public interface ICompiler {
    JudgeResult.Status compile(String code,String version) throws CodeCompileException;

    JudgeResult run(String input,Long timeLimit,Long memoryLimit) throws CodeRunException;

    void removeFiles();

    JudgeParam runParam(String input,Long timeLimit, Long memoryLimit);

    JudgeParam compileParam(String code, String version);
}
