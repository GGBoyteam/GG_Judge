package com.zhangsiyao.judge.handle;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.exception.JudgeRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JudgeRequestExceptionHandle {

    @ExceptionHandler(value = JudgeRequestException.class)
    public R<String> handle(){
        return R.error("向判题机发送请求失败，请检查判题机是否正常运行！");
    }

}
