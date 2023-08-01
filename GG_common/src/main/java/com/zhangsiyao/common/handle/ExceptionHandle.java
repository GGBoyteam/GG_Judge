package com.zhangsiyao.common.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.zhangsiyao")
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public R<String> handle(Exception e){
        return R.error(e.getMessage());
    }
}
