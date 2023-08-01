package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.service.dao.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.service.dto.RolePermissionDto;
import com.zhangsiyao.common.entity.service.vo.RoleQueryVo;
import com.zhangsiyao.common.entity.service.vo.RoleAddOrUpdateVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
public interface IRoleService extends IService<Role> {

    Page<Role> list(RoleQueryVo roleQueryVo);
    RolePermissionDto selected(String id);

    void update(RoleAddOrUpdateVo roleAddOrUpdateVo);

    void add(RoleAddOrUpdateVo roleAddOrUpdateVo);

    void delete(String[] id);

    void changeStatus(Long roleId,Long status);

}
