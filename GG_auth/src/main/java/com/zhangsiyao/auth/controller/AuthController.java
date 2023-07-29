package com.zhangsiyao.auth.controller;

import com.zhangsiyao.auth.entity.dto.AuthResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.auth.service.IUserloginService;
import com.zhangsiyao.common.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IUserloginService userloginService;

    @PostMapping("/login")
    public R<AuthResultDto> passwordLogin(@RequestBody UserPasswordVo userPasswordVo){
        return userloginService.loginByPassword(userPasswordVo);
    }

    @PostMapping("/register")
    public R<AuthResultDto> register(@RequestBody UserPasswordVo userPasswordVo){
        return userloginService.register(userPasswordVo);
    }

    @PostMapping("/logout")
    public R<String> logout(@RequestHeader("Authorization") String token){
        return  userloginService.logout(token);
    }
}
