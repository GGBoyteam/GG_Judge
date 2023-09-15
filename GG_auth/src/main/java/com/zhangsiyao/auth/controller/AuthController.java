package com.zhangsiyao.auth.controller;

import com.zhangsiyao.common.entity.auth.dto.AuthResultDto;
import com.zhangsiyao.common.entity.auth.vo.UserLoginAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.auth.service.IAuthService;
import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService userloginService;

    @PostMapping("/login")
    public R<AuthResultDto> passwordLogin(@RequestBody UserPasswordVo userPasswordVo)  {
        return R.success(userloginService.loginByPassword(userPasswordVo));
    }

    @PostMapping("/register")
    public R<AuthResultDto> register(@RequestBody UserPasswordVo userPasswordVo){
        userloginService.register(userPasswordVo);
        return R.success();
    }

    @PostMapping("/logout")
    public R<String> logout(@RequestHeader("Authorization") String token){
        userloginService.logout(token);
        return  R.success();
    }

    @PostMapping("/addOrUpdate")
    public R<String> saveOrUpdate(@RequestBody UserLoginAddOrUpdate addOrUpdate){
        userloginService.addOrUpdate(addOrUpdate);
        return R.success();
    }

    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable String id){
        userloginService.delete(id);
        return R.success();
    }

    @PostMapping("/resetPwd")
    public R<String> resetPwd(@RequestBody UserPasswordVo passwordVo){
        userloginService.resetPwd(passwordVo);
        return R.success();
    }

}
