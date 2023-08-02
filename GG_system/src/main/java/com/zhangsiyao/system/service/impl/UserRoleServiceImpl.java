package com.zhangsiyao.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangsiyao.common.entity.service.dao.Role;
import com.zhangsiyao.common.entity.service.dao.UserRole;
import com.zhangsiyao.common.entity.service.dto.RoleDto;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
import com.zhangsiyao.system.mapper.UserRoleMapper;
import com.zhangsiyao.system.service.IRoleService;
import com.zhangsiyao.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    IRoleService roleService;

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
}
