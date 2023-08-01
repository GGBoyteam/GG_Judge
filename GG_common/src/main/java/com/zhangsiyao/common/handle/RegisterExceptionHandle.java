package com.zhangsiyao.common.handle;


import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.exception.RegisterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.zhangsiyao")
public class RegisterExceptionHandle {

    @ExceptionHandler(value = RegisterException.class)
    public R<String> handleRegisterException(RegisterException e){
        return R.error(e.getMessage());
    }

}
