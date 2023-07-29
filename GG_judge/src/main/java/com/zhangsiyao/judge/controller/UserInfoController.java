package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dto.UserInfoDto;
import com.zhangsiyao.judge.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping("/getInfo")
    public R<UserInfoDto> getInfo(@RequestHeader("Authorization") String token){
        return userInfoService.getInfo(token);
    }
}
