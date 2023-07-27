package com.zhangsiyao.auth.controller;

import com.zhangsiyao.auth.entity.dto.LoginResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.common.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public R<LoginResultDto> passwordLogin(UserPasswordVo userPasswordVo){
        return null;
    }

    @PostMapping("/register")
    public R<LoginResultDto> register(UserPasswordVo userPasswordVo){
        return null;
    }
}
