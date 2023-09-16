package com.zhangsiyao.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.auth.component.Md5Component;
import com.zhangsiyao.common.component.JwtComponent;
import com.zhangsiyao.common.entity.auth.dao.Auth;
import com.zhangsiyao.common.entity.auth.dto.AuthResultDto;
import com.zhangsiyao.common.entity.auth.vo.AuthAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.exception.LoginException;
import com.zhangsiyao.common.exception.RegisterException;
import com.zhangsiyao.auth.mapper.AuthMapper;
import com.zhangsiyao.auth.service.IAuthService;
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
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {

    @Autowired
    Md5Component md5Component;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    JwtComponent jwtComponent;

    @SneakyThrows
    @Override
    public void register(UserPasswordVo passwordVo) {
        List<Auth> list = this.query().eq("username",passwordVo.getUsername()).list();
        if(list.size()>0){
            throw new RegisterException("账号已经被注册");
        }
        Auth auth=new Auth();
        auth.setUsername(passwordVo.getUsername());
        auth.setPassword(md5Component.md5Hex(passwordVo.getPassword()));
        this.save(auth);
    }

    @SneakyThrows
    @Override
    public AuthResultDto loginByPassword(UserPasswordVo passwordVo) {
        Auth user = this.query().eq("username", passwordVo.getUsername()).one();
        String inputPassword=md5Component.md5Hex(passwordVo.getPassword());
        if(user==null){
            throw new LoginException("登录失败，账号不存在");
        }
        if(!user.getPassword().equals(inputPassword)){
            throw new LoginException("登录失败，密码错误");
        }
        String token = jwtComponent.createToken(user.getUsername());
        redisTemplate.opsForValue().set(token,user.getUsername(),3,TimeUnit.HOURS);
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
    public void addOrUpdate(AuthAddOrUpdate addOrUpdate) {
        Auth id = this.getById(addOrUpdate.getUsername());
        if(id==null){
            id=new Auth();
        }
        BeanUtils.copyProperties(addOrUpdate,id);
        id.setPassword(md5Component.md5Hex(addOrUpdate.getPassword()));
        this.saveOrUpdate(id);
    }

    @Override
    public void delete(String id) {
        this.getBaseMapper().deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(UserPasswordVo passwordVo) {
        Auth auth = this.getById(passwordVo.getUsername());
        auth.setPassword(md5Component.md5Hex(passwordVo.getPassword()));
        this.updateById(auth);
    }
}
