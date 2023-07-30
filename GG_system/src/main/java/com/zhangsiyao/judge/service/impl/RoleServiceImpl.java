package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.judge.entity.dao.Role;
import com.zhangsiyao.judge.entity.dao.RolePermission;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.RolePermissionDto;
import com.zhangsiyao.judge.entity.vo.RoleQueryVo;
import com.zhangsiyao.judge.entity.vo.RoleAddOrUpdateVo;
import com.zhangsiyao.judge.mapper.RoleMapper;
import com.zhangsiyao.judge.service.IRolePermissionService;
import com.zhangsiyao.judge.service.IRoleService;
import com.zhangsiyao.judge.service.IRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    IRouteService routeService;

    @Autowired
    IRolePermissionService rolePermissionService;


    @Override
    public Page<Role> list(RoleQueryVo roleQueryVo) {
        Page<Role> page=Page.of(roleQueryVo.getPageNum(),roleQueryVo.getPageSize());
        LambdaQueryWrapper<Role> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(roleQueryVo.getRoleName())){
            lambdaQueryWrapper=lambdaQueryWrapper.like(Role::getName,roleQueryVo.getRoleName());
        }
        if(!StringUtils.isEmpty(roleQueryVo.getRoleKey())){
            lambdaQueryWrapper=lambdaQueryWrapper.like(Role::getRoleKey,roleQueryVo.getRoleKey());
        }
        if(roleQueryVo.getStatus()!=null){
            lambdaQueryWrapper=lambdaQueryWrapper.eq(Role::getStatus,roleQueryVo.getStatus());
        }
        return this.getBaseMapper().selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public RolePermissionDto selected(String id) {
        RolePermissionDto rolePermissionDto=new RolePermissionDto();
        rolePermissionDto.setMenus(routeService.getRouteTree());
        LambdaQueryWrapper<RolePermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(RolePermission::getRoleId,id);
        List<RolePermission> rolePermissions = rolePermissionService.getBaseMapper().selectList(queryWrapper);
        List<Long> checks=new ArrayList<>();
        rolePermissions.forEach(rolePermission -> checks.add(rolePermission.getRouteId()));
        rolePermissionDto.setCheckedKeys(checks);
        return rolePermissionDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(RoleAddOrUpdateVo roleAddOrUpdateVo) {
        Role role=new Role();
        BeanUtils.copyProperties(roleAddOrUpdateVo,role);
        this.getBaseMapper().updateById(role);


        //先删除旧的所以权限信息
        LambdaQueryWrapper<RolePermission> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RolePermission::getRoleId,role.getId());
        rolePermissionService.remove(lambdaQueryWrapper);

        if(roleAddOrUpdateVo.getRouteIds().size()==0){
            return true;
        }

        //然后设置新的权限信息
        LambdaQueryWrapper<Route> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.in(Route::getId, roleAddOrUpdateVo.getRouteIds());
        List<Route> routes = routeService.getBaseMapper().selectList(queryWrapper);
        for(Route route:routes){
            RolePermission permission=new RolePermission();
            permission.setRoleId(role.getId());
            permission.setRouteId(route.getId());
            permission.setPermission(route.getPermission());
            rolePermissionService.saveOrUpdate(permission);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(RoleAddOrUpdateVo roleAddOrUpdateVo) {
        Role role=new Role();
        BeanUtils.copyProperties(roleAddOrUpdateVo,role);
        this.getBaseMapper().insert(role);
        //然后设置新的权限信息
        LambdaQueryWrapper<Route> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.in(Route::getId, roleAddOrUpdateVo.getRouteIds());
        List<Route> routes = routeService.getBaseMapper().selectList(queryWrapper);
        for(Route route:routes){
            RolePermission permission=new RolePermission();
            permission.setRoleId(role.getId());
            permission.setRouteId(route.getId());
            permission.setPermission(route.getPermission());
            rolePermissionService.saveOrUpdate(permission);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        this.getBaseMapper().deleteById(id);
        LambdaQueryWrapper<RolePermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(RolePermission::getRoleId, id);
        rolePermissionService.getBaseMapper().delete(queryWrapper);
        return false;
    }

    @Override
    public void changeStatus(Long roleId, Long status) {
        LambdaQueryWrapper<Role> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Role::getId,roleId);
        Role role = this.getBaseMapper().selectOne(queryWrapper);
        role.setStatus(Integer.parseInt(String.valueOf(status)));
        this.getBaseMapper().updateById(role);
    }


}