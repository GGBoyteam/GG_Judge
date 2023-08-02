package com.zhangsiyao.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import com.zhangsiyao.common.entity.auth.vo.UserLoginAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.service.dao.RolePermission;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import com.zhangsiyao.common.entity.service.dao.UserRole;
import com.zhangsiyao.common.entity.service.dto.UserInfoDto;
import com.zhangsiyao.common.entity.service.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.service.vo.UserQueryVo;
import com.zhangsiyao.common.exception.RegisterException;
import com.zhangsiyao.common.service.feign.UserAuthService;
import com.zhangsiyao.system.mapper.UserInfoMapper;
import com.zhangsiyao.system.service.IRolePermissionService;
import com.zhangsiyao.system.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.system.service.IUserRoleService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

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
            UserLogin user = objectMapper.readValue(redisTemplate.opsForValue().get(token), UserLogin.class);
            LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper=new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername,user.getUsername());
            UserInfo one = this.getOne(userInfoLambdaQueryWrapper);
            userPermissionDto.setInfo(one);
            LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,one.getId());
            List<UserRole> userRoles=userRoleService.list(queryWrapper);
            List<RolePermission> permissions =new ArrayList<>();
            for (UserRole userRole:userRoles){
                LambdaQueryWrapper<RolePermission> permissionQueryWrapper=new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId,userRole.getRoleId());
                permissions.addAll(permissionService.list(permissionQueryWrapper));
            }
            userPermissionDto.setPermissions(permissions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return R.success(userPermissionDto);
    }

    @Override
    public Page<UserInfo> list(UserQueryVo queryVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper=new LambdaQueryWrapper<>();
        Page<UserInfo> page=Page.of(queryVo.getPageNum(), queryVo.getPageSize());
        if(!StringUtils.isEmpty(queryVo.getPhone())){
            queryWrapper=queryWrapper.like(UserInfo::getPhone,queryVo.getPhone());
        }
        if(!StringUtils.isEmpty(queryVo.getUsername())){
            queryWrapper=queryWrapper.like(UserInfo::getUsername,queryVo.getUsername());
        }
        if(queryVo.getStatus()!=null){
            queryWrapper=queryWrapper.eq(UserInfo::getStatus,queryVo.getStatus());
        }
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddOrUpdateVo addOrUpdateVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, addOrUpdateVo.getUsername());
        if(this.count(queryWrapper)!=0){
            throw new RegisterException("该账户经存在");
        }
        UserLoginAddOrUpdate passwordVo=new UserLoginAddOrUpdate();
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
        UserInfo info = this.getById(addOrUpdateVo.getId());
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
    public UserInfoDto info(String id) {
        UserInfo info = this.getById(id);
        UserInfoDto userInfoDto=new UserInfoDto();
        BeanUtils.copyProperties(info,userInfoDto);
        List<Long> roleIds=new ArrayList<>();
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,id);
        userRoleService.getBaseMapper().selectList(queryWrapper).forEach(userRole -> roleIds.add(userRole.getRoleId()));
        userInfoDto.setRoleIds(roleIds);
        return userInfoDto;
    }

}
