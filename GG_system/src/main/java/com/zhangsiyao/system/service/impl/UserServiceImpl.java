package com.zhangsiyao.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.vo.AuthAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.system.dao.RolePermission;
import com.zhangsiyao.common.entity.system.dao.User;
import com.zhangsiyao.common.entity.system.dao.UserRole;
import com.zhangsiyao.common.entity.system.dto.UserDto;
import com.zhangsiyao.common.entity.system.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.system.vo.UserQueryVo;
import com.zhangsiyao.common.exception.RegisterException;
import com.zhangsiyao.common.service.feign.UserAuthService;
import com.zhangsiyao.system.mapper.UserMapper;
import com.zhangsiyao.system.service.IRolePermissionService;
import com.zhangsiyao.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.system.service.IUserRoleService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IRolePermissionService permissionService;

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserAuthService userAuthService;

    @Override
    public R<UserPermissionDto> getInfo(String token) {
        UserPermissionDto userPermissionDto =new UserPermissionDto();
        try {
            String username = objectMapper.readValue(redisTemplate.opsForValue().get(token), String.class);
            LambdaQueryWrapper<User> userInfoLambdaQueryWrapper=new LambdaQueryWrapper<User>().eq(User::getUsername,username);
            User one = this.getOne(userInfoLambdaQueryWrapper);
            userPermissionDto.setInfo(one);
            LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,one.getId());
            List<UserRole> userRoles=userRoleService.list(queryWrapper);
            List<String> permissions =new ArrayList<>();
            for (UserRole userRole:userRoles){
                LambdaQueryWrapper<RolePermission> permissionQueryWrapper=new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId,userRole.getRoleId());
                permissionService.list(permissionQueryWrapper).forEach(
                        entity->{
                            permissions.add(entity.getPermission());
                        }
                );
            }
            userPermissionDto.setPermissions(permissions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return R.success(userPermissionDto);
    }

    @Override
    public Page<User> list(UserQueryVo queryVo) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        Page<User> page=Page.of(queryVo.getPageNum(), queryVo.getPageSize());
        if(!StringUtils.isEmpty(queryVo.getPhone())){
            queryWrapper=queryWrapper.like(User::getPhone,queryVo.getPhone());
        }
        if(!StringUtils.isEmpty(queryVo.getUsername())){
            queryWrapper=queryWrapper.like(User::getUsername,queryVo.getUsername());
        }
        if(queryVo.getStatus()!=null){
            queryWrapper=queryWrapper.eq(User::getStatus,queryVo.getStatus());
        }
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddOrUpdateVo addOrUpdateVo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, addOrUpdateVo.getUsername());
        if(this.count(queryWrapper)!=0){
            throw new RegisterException("该账户经存在");
        }
        AuthAddOrUpdate passwordVo=new AuthAddOrUpdate();
        passwordVo.setUsername(addOrUpdateVo.getUsername());
        passwordVo.setPassword(addOrUpdateVo.getPassword());
        userAuthService.register(passwordVo);
        this.save(addOrUpdateVo);
        for(Long roleId:addOrUpdateVo.getRoleIds()){
            UserRole userRole=new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(addOrUpdateVo.getId());
            userRoleService.save(userRole);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String[] ids) {
       for(String id:ids){
           userAuthService.delete(this.getById(id).getUsername());
           this.getBaseMapper().deleteById(id);
       }
    }

    @Override
    public void update(UserAddOrUpdateVo addOrUpdateVo) {
        User info = this.getById(addOrUpdateVo.getId());
        if(info==null){
            return;
        }
        addOrUpdateVo.setUsername(info.getUsername());
        this.updateById(addOrUpdateVo);
        for(Long roleId:addOrUpdateVo.getRoleIds()){
            LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId,roleId).eq(UserRole::getUserId,info.getId());
            if(userRoleService.count(queryWrapper)!=0){
                continue;
            }
            UserRole userRole=new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(addOrUpdateVo.getId());
            userRoleService.save(userRole);
        }
    }

    @Override
    public void resetPwd(UserPasswordVo userPasswordVo) {
        userAuthService.resetPwd(userPasswordVo);
    }

    @Override
    public UserDto info(String id) {
        User info = this.getById(id);
        UserDto userInfoDto=new UserDto();
        BeanUtils.copyProperties(info,userInfoDto);
        List<Long> roleIds=new ArrayList<>();
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,id);
        userRoleService.getBaseMapper().selectList(queryWrapper).forEach(userRole -> roleIds.add(userRole.getRoleId()));
        userInfoDto.setRoleIds(roleIds);
        return userInfoDto;
    }

    @Override
    public List<String> permissions(String username) {
        LambdaQueryWrapper<User> userInfoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper=userInfoLambdaQueryWrapper.eq(User::getUsername,username);
        User user = this.baseMapper.selectOne(userInfoLambdaQueryWrapper);
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId());
        List<UserRole> userRoles=userRoleService.list(queryWrapper);
        Set<String> permissions =new HashSet<>();
        for (UserRole userRole:userRoles){
            LambdaQueryWrapper<RolePermission> permissionQueryWrapper=new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId,userRole.getRoleId());
            permissionService.list(permissionQueryWrapper).forEach(
                    entity->{
                        permissions.add(entity.getPermission());
                    }
            );
        }
        return new ArrayList<>(permissions);
    }

}
