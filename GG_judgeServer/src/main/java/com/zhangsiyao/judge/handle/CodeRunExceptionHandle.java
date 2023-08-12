package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.CodeRunException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CodeRunExceptionHandle {

    @ExceptionHandler(value = CodeRunException.class)
    public R<JudgeResult> handle(CodeRunException exception){
        if(exception.getCompiler()!=null){
            exception.getCompiler().removeFiles();
        }
        return R.error(exception.getMessage(),exception.getJudgeResult());
    }

}
