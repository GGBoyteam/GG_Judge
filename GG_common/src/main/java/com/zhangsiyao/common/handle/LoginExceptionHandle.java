package com.zhangsiyao.common.handle;


import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.exception.LoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.zhangsiyao")
public class LoginExceptionHandle {

    @ExceptionHandler(value = {LoginException.class})
    public R<String> handleLoginException(LoginException e){
        return R.error(e.getMessage());
    }
}
