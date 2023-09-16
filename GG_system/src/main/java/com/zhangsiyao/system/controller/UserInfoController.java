package com.zhangsiyao.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.system.dao.User;
import com.zhangsiyao.common.entity.system.dto.UserDto;
import com.zhangsiyao.common.entity.system.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.system.vo.UserQueryVo;
import com.zhangsiyao.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserService userInfoService;

    @GetMapping("/getInfo")
    public R<UserPermissionDto> getInfo(@RequestHeader("Authorization") String token){
        return userInfoService.getInfo(token);
    }

    @GetMapping("/list")
    public R<Page<User>> list(UserQueryVo queryVo){
        return R.success(userInfoService.list(queryVo));
    }



    @PostMapping("/add")
    public R<String> add(@RequestBody UserAddOrUpdateVo addOrUpdateVo){
        userInfoService.add(addOrUpdateVo);
        return R.success();
    }

    @PostMapping("/update")
    public R<String> update(@RequestBody UserAddOrUpdateVo addOrUpdateVo){
        userInfoService.update(addOrUpdateVo);
        return R.success();
    }

    @GetMapping("/info/{id}")
    public R<UserDto> info(@PathVariable String id){
        return R.success(userInfoService.info(id));
    }

    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable String[] id){
        userInfoService.delete(id);
        return R.success();
    }

    @PostMapping("/resetPwd")
    public R<String> resetPwd(@RequestBody UserPasswordVo userPasswordVo){
        userInfoService.resetPwd(userPasswordVo);
        return R.success();
    }

}
