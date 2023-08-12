package com.zhangsiyao.judge.exception;

import com.zhangsiyao.judge.compiler.ICompiler;
import com.zhangsiyao.judge.compiler.JudgeResult;

public class CodeRunException extends Exception{

    JudgeResult judgeResult;

    ICompiler compiler;
    public CodeRunException(String message, JudgeResult judgeResult, ICompiler compiler) {
        super(message);
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }

    public ICompiler getCompiler() {
        return compiler;
    }
}
