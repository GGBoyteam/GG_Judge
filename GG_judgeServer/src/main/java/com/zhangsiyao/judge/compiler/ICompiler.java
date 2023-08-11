package com.zhangsiyao.judge.compiler;

public interface ICompiler {
    JudgeResult compile
            (String code,Integer version);

    JudgeResult run(String input,Long timeLimit,Long memoryLimit);

    void removeFiles();
}
