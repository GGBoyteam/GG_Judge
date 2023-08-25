package com.zhangsiyao.system.feign;

import com.zhangsiyao.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign/user")
public class UserInfoFeign {

    @Autowired
    IUserInfoService userInfoService;
    @GetMapping("/permissions/{username}")
    public List<String> permissions(@PathVariable String username){
        return userInfoService.permissions(username);
    }
}
