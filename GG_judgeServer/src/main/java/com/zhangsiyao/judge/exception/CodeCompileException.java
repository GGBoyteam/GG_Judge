package com.zhangsiyao.judge.exception;

import com.zhangsiyao.judge.compiler.ICompiler;
import com.zhangsiyao.judge.compiler.JudgeResult;

public class CodeCompileException extends Exception{

    JudgeResult judgeResult;

    ICompiler compiler;

    public CodeCompileException(String message, JudgeResult judgeResult, ICompiler compiler) {
        super(message);
        this.judgeResult=judgeResult;
        this.compiler=compiler;
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }

    public ICompiler getCompiler() {
        return compiler;
    }
}
