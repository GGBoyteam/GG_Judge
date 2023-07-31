package com.zhangsiyao.auth.handle;

import com.zhangsiyao.auth.exception.RegisterException;
import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegisterExceptionHandle {

    @ExceptionHandler(value = RegisterException.class)
    public R<String> handleRegisterException(RegisterException e){
        return R.error(e.getMessage());
    }

}
