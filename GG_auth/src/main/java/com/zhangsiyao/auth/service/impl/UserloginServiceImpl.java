package com.zhangsiyao.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.auth.component.Md5Component;
import com.zhangsiyao.common.component.JwtComponent;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import com.zhangsiyao.common.entity.auth.dto.AuthResultDto;
import com.zhangsiyao.common.entity.auth.vo.UserLoginAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.exception.LoginException;
import com.zhangsiyao.common.exception.RegisterException;
import com.zhangsiyao.auth.mapper.UserloginMapper;
import com.zhangsiyao.auth.service.IUserloginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @SneakyThrows
    @Override
    public void register(UserPasswordVo passwordVo) {
        List<UserLogin> list = this.query().eq("username",passwordVo.getUsername()).list();
        if(list.size()>0){
            throw new RegisterException("账号已经被注册");
        }
        UserLogin userlogin=new UserLogin();
        userlogin.setUsername(passwordVo.getUsername());
        userlogin.setPassword(md5Component.md5Hex(passwordVo.getPassword()));
        this.save(userlogin);
    }

    @SneakyThrows
    @Override
    public AuthResultDto loginByPassword(UserPasswordVo passwordVo) {
        UserLogin user = this.query().eq("username", passwordVo.getUsername()).one();
        String inputPassword=md5Component.md5Hex(passwordVo.getPassword());
        if(user==null){
            throw new LoginException("登录失败，账号不存在");
        }
        if(!user.getPassword().equals(inputPassword)){
            throw new LoginException("登录失败，密码错误");
        }
        String token = jwtComponent.createToken(user.getUsername());
        ObjectMapper objectMapper=new ObjectMapper();
        redisTemplate.opsForValue().set(token,objectMapper.writeValueAsString(user),3,TimeUnit.HOURS);
        AuthResultDto authResultDto=new AuthResultDto();
        authResultDto.setToken(token);
        return authResultDto;
    }


    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(UserLoginAddOrUpdate addOrUpdate) {
        UserLogin id = this.getById(addOrUpdate.getUsername());
        if(id==null){
            id=new UserLogin();
        }
        BeanUtils.copyProperties(addOrUpdate,id);
        id.setPassword(md5Component.md5Hex(addOrUpdate.getPassword()));
        this.saveOrUpdate(id);
    }

    @Override
    public void delete(String id) {
        this.getBaseMapper().deleteById(id);
    }
}
