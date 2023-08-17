package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.exception.AnswerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnswerExceptionHandle {
    @ExceptionHandler(value = AnswerException.class)
    public R<AnswerException.AnswerExceptionDto> handle( AnswerException e){
        return R.error(e.getAnswerExceptionDto());
    }

}
