package com.zhangsiyao.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import com.zhangsiyao.common.entity.service.dto.UserInfoDto;
import com.zhangsiyao.common.entity.service.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.service.vo.UserQueryVo;
import com.zhangsiyao.system.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;

    @GetMapping("/getInfo")
    public R<UserPermissionDto> getInfo(@RequestHeader("Authorization") String token){
        return userInfoService.getInfo(token);
    }

    @GetMapping("/list")
    public R<Page<UserInfo>> list(UserQueryVo queryVo){
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
    public R<UserInfoDto> info(@PathVariable String id){
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
