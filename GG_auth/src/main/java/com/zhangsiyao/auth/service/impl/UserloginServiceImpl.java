package com.zhangsiyao.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.auth.component.Md5Component;
import com.zhangsiyao.auth.entity.dao.Role;
import com.zhangsiyao.auth.entity.dao.RolePermission;
import com.zhangsiyao.auth.entity.dao.Userlogin;
import com.zhangsiyao.auth.entity.dto.AuthResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.auth.mapper.UserloginMapper;
import com.zhangsiyao.auth.service.IRolePermissionService;
import com.zhangsiyao.auth.service.IRoleService;
import com.zhangsiyao.auth.service.IUserloginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.common.component.JwtComponent;
import com.zhangsiyao.common.result.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class UserloginServiceImpl extends ServiceImpl<UserloginMapper, Userlogin> implements IUserloginService {

    @Autowired
    Md5Component md5Component;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IRoleService roleService;

    @Autowired
    IRolePermissionService permissionService;

    @Autowired
    JwtComponent jwtComponent;

    @Override
    public R<AuthResultDto> register(UserPasswordVo passwordVo) {
        List<Userlogin> list = this.query().eq("username",passwordVo.getUsername()).list();
        if(list.size()>0){
            return R.error("账号已经被注册");
        }
        Userlogin userlogin=new Userlogin();
        userlogin.setUsername(passwordVo.getUsername());
        userlogin.setPassword(md5Component.md5Hex(passwordVo.getPassword()));
        this.save(userlogin);
        return loginByPassword(passwordVo);
    }

    @SneakyThrows
    @Override
    public R<AuthResultDto> loginByPassword(UserPasswordVo passwordVo) {
        System.out.println(passwordVo);
        Userlogin user = this.query().eq("username", passwordVo.getUsername()).one();
        System.out.println(user);
        String inputPassword=md5Component.md5Hex(passwordVo.getPassword());
        if(user==null){
            return R.error("登录失败，账号不存在");
        }
        if(!user.getPassword().equals(inputPassword)){
            return R.error("登录失败，密码错误");
        }
        Map<Object, Object> data = loadAllData(passwordVo.getUsername());
        ObjectMapper objectMapper=new ObjectMapper();
        String token = jwtComponent.createToken(passwordVo.getUsername());
        String dataJSon=objectMapper.writeValueAsString(data);
        redisTemplate.opsForValue().set(token,dataJSon,3,TimeUnit.HOURS);
        AuthResultDto authResultDto=new AuthResultDto();
        authResultDto.setToken(token);
        return R.success(" 登入成功",authResultDto);
    }

    @Override
    public Map<Object, Object> loadAllData(String username) {
        Map<Object,Object> map=new HashMap<>();
        Userlogin one = this.query().eq("username", username).one();
        Role role = roleService.getById(one.getId());
        List<RolePermission> permissions = permissionService.query().eq("role_id", role.getId()).list();
        map.put("info",one);
        map.put("role",role);
        map.put("permissions",permissions);
        return map;
    }
}
