package com.zhangsiyao.auth.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class LoginExceptionHandle {

    @ExceptionHandler(value = LoginException.class)
    public R<String> handleLoginException(LoginException e){
        return R.error(e.getMessage());
    }
}
