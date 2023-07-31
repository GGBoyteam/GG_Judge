package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.UserInfo;
import com.zhangsiyao.judge.entity.dto.UserInfoDto;
import com.zhangsiyao.judge.entity.vo.UserAddOrUpdateVo;
import com.zhangsiyao.judge.entity.vo.UserQueryVo;
import com.zhangsiyao.judge.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping("/getInfo")
    public R<UserInfoDto> getInfo(@RequestHeader("Authorization") String token){
        return userInfoService.getInfo(token);
    }

    @GetMapping("/list")
    public R<Page<UserInfo>> list(UserQueryVo queryVo){
        return R.success(userInfoService.list(queryVo));
    }

    @PostMapping("/add")
    public R<String> add(UserAddOrUpdateVo addOrUpdateVo){
        userInfoService.addOrUpdate(addOrUpdateVo);
        return R.success();
    }
}
