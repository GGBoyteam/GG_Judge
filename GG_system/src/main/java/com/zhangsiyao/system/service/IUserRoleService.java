package com.zhangsiyao.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.system.dao.Role;
import com.zhangsiyao.common.entity.system.dao.User;
import com.zhangsiyao.common.entity.system.dao.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.system.dto.RoleDto;
import com.zhangsiyao.common.entity.system.vo.RoleAllocateUserVo;
import com.zhangsiyao.common.entity.system.vo.RoleUserQueryVo;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-02
 */
public interface IUserRoleService extends IService<UserRole> {

    List<RoleDto> getRoleByUserId(String id);

    void update(UserAddOrUpdateVo addOrUpdateVo);

    Page<User> allocateUserList(RoleAllocateUserVo allocateUserVo);

    Page<User> unallocateUserList(RoleAllocateUserVo allocateUserVo);

    Page<Role> listByUserId(RoleUserQueryVo roleUserQueryVo);
}
