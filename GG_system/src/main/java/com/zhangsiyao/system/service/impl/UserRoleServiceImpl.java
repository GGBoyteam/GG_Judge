package com.zhangsiyao.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.system.dao.Role;
import com.zhangsiyao.common.entity.system.dao.User;
import com.zhangsiyao.common.entity.system.dao.UserRole;
import com.zhangsiyao.common.entity.system.dto.RoleDto;
import com.zhangsiyao.common.entity.system.vo.RoleAllocateUserVo;
import com.zhangsiyao.common.entity.system.vo.RoleUserQueryVo;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;
import com.zhangsiyao.system.mapper.UserRoleMapper;
import com.zhangsiyao.system.service.IRoleService;
import com.zhangsiyao.system.service.IUserService;
import com.zhangsiyao.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-08-02
 */
@Service
@SuppressWarnings("all")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userInfoService;

    @Override
    public List<RoleDto> getRoleByUserId(String id) {
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,id);
        Set<Long> roleIds=new HashSet<>();
        this.baseMapper.selectList(queryWrapper).forEach((userRole -> roleIds.add(userRole.getRoleId())));
        LambdaQueryWrapper<Role> roleQueryWrapper=new LambdaQueryWrapper<Role>().in(Role::getId,roleIds);
        List<RoleDto> roles = new ArrayList<>();
        roleService.getBaseMapper().selectList(roleQueryWrapper).forEach(role ->{
            RoleDto roleDto=new RoleDto();
            BeanUtils.copyProperties(role,roleDto);
            roles.add(roleDto);
        });
        return roles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserAddOrUpdateVo addOrUpdateVo) {
        Long userId=addOrUpdateVo.getId();
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,userId);
        this.baseMapper.delete(queryWrapper);
        for(Long role:addOrUpdateVo.getRoleIds()){
            UserRole userRole=new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(role);
            this.save(userRole);
        }
    }

    @Override
    public Page<User> allocateUserList(RoleAllocateUserVo allocateUserVo) {
        Page<User> page;
        if(allocateUserVo==null||allocateUserVo.getPageNum()==null||allocateUserVo.getPageSize()==null){
            page=Page.of(1,Long.MAX_VALUE);
        }else {
            page=Page.of(allocateUserVo.getPageNum(),allocateUserVo.getPageSize());
        }
        LambdaQueryWrapper<UserRole> queryWrapper= new LambdaQueryWrapper<>();
        if(allocateUserVo!=null&&allocateUserVo.getRoleId()!=null){
           queryWrapper=queryWrapper.eq(UserRole::getRoleId,allocateUserVo.getRoleId());
        }

        Set<Long> userIds=new HashSet<>();
        userIds.add(0L);
        this.baseMapper.selectList(queryWrapper).forEach(userRole -> userIds.add(userRole.getUserId()));
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(userIds.size()>0){
            lambdaQueryWrapper=lambdaQueryWrapper.in(User::getId,userIds);
        }
        if(allocateUserVo!=null&&allocateUserVo.getUsername()!=null){
            lambdaQueryWrapper=lambdaQueryWrapper.like(User::getUsername,allocateUserVo.getUsername());
        }
        if(allocateUserVo!=null&&allocateUserVo.getPhone()!=null){
            lambdaQueryWrapper=lambdaQueryWrapper.like(User::getPhone,allocateUserVo.getPhone());
        }
        return userInfoService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
    }

    @Override
    public Page<User> unallocateUserList(RoleAllocateUserVo allocateUserVo) {
        Page<User> page;
        if(allocateUserVo==null||allocateUserVo.getPageNum()==null||allocateUserVo.getPageSize()==null){
            page=Page.of(1,Long.MAX_VALUE);
        }else {
            page=Page.of(allocateUserVo.getPageNum(),allocateUserVo.getPageSize());
        }
        LambdaQueryWrapper<UserRole> queryWrapper= new LambdaQueryWrapper<>();
        if(allocateUserVo!=null&&allocateUserVo.getRoleId()!=null){
            queryWrapper=queryWrapper.eq(UserRole::getRoleId,allocateUserVo.getRoleId());
        }
        Set<Long> userIds=new HashSet<>();
        this.baseMapper.selectList(queryWrapper).forEach(userRole -> userIds.add(userRole.getUserId()));
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(userIds.size()>0){
            lambdaQueryWrapper=lambdaQueryWrapper.notIn(User::getId,userIds);
        }
        if(allocateUserVo!=null&&allocateUserVo.getUsername()!=null){
            lambdaQueryWrapper=lambdaQueryWrapper.like(User::getUsername,allocateUserVo.getUsername());
        }
        if(allocateUserVo!=null&&allocateUserVo.getPhone()!=null){
            lambdaQueryWrapper=lambdaQueryWrapper.like(User::getPhone,allocateUserVo.getPhone());
        }
        return userInfoService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
    }

    @Override
    public Page<Role> listByUserId(RoleUserQueryVo roleUserQueryVo) {
        return null;
    }

}
