package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.CodeCompileException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CodeCompileExceptionHandle {

    @ExceptionHandler(value = CodeCompileException.class)
    public R<JudgeResult> handle(CodeCompileException exception){
       if(exception.getCompiler()!=null){
           exception.getCompiler().removeFiles();
       }
        return R.error(exception.getMessage(),exception.getJudgeResult());
    }
}
