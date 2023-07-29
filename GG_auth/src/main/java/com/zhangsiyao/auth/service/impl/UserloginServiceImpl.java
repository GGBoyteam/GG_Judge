package com.zhangsiyao.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.auth.component.Md5Component;
import com.zhangsiyao.auth.entity.dao.UserLogin;
import com.zhangsiyao.auth.entity.dto.AuthResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.auth.mapper.UserloginMapper;
import com.zhangsiyao.auth.service.IUserloginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.common.component.JwtComponent;
import com.zhangsiyao.common.result.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
@Service
public class UserloginServiceImpl extends ServiceImpl<UserloginMapper, UserLogin> implements IUserloginService {

    @Autowired
    Md5Component md5Component;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    JwtComponent jwtComponent;

    @Override
    public R<AuthResultDto> register(UserPasswordVo passwordVo) {
        List<UserLogin> list = this.query().eq("username",passwordVo.getUsername()).list();
        if(list.size()>0){
            return R.error("账号已经被注册");
        }
        UserLogin userlogin=new UserLogin();
        userlogin.setUsername(passwordVo.getUsername());
        userlogin.setPassword(md5Component.md5Hex(passwordVo.getPassword()));
        this.save(userlogin);
        return loginByPassword(passwordVo);
    }

    @SneakyThrows
    @Override
    public R<AuthResultDto> loginByPassword(UserPasswordVo passwordVo) {
        UserLogin user = this.query().eq("username", passwordVo.getUsername()).one();
        String inputPassword=md5Component.md5Hex(passwordVo.getPassword());
        if(user==null){
            return R.error("登录失败，账号不存在");
        }
        if(!user.getPassword().equals(inputPassword)){
            return R.error("登录失败，密码错误");
        }
        String token = jwtComponent.createToken(user.getUsername());
        ObjectMapper objectMapper=new ObjectMapper();
        redisTemplate.opsForValue().set(token,objectMapper.writeValueAsString(user),3,TimeUnit.HOURS);
        AuthResultDto authResultDto=new AuthResultDto();
        authResultDto.setToken(token);
        return R.success(" 登入成功",authResultDto);
    }


    @Override
    public R<String> logout(String token) {
        redisTemplate.delete(token);
        return R.success();
    }
}
