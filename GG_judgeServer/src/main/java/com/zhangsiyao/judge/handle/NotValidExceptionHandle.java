package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.zhangsiyao")
public class NotValidExceptionHandle {
    // 数据校验异常
    @ExceptionHandler(value = BindException.class)
    public R handleValidException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach( item -> {
            errorMap.put(item.getField(),item.getDefaultMessage());
        } );
        return R.error("数据校验异常",errorMap);
    }
}
