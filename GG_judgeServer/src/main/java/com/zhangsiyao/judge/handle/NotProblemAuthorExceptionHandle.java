package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotProblemAuthorExceptionHandle {

    @ExceptionHandler(value = NotProblemAuthorException.class)
    public R<String> handle(NotProblemAuthorException exception){
        return R.error(exception.getMessage());
    }

}
