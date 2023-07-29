package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.judge.entity.dao.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.judge.entity.dto.RolePermissionDto;
import com.zhangsiyao.judge.entity.vo.RoleQueryVo;
import com.zhangsiyao.judge.entity.vo.RoleAddOrUpdateVo;

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

    boolean update(RoleAddOrUpdateVo roleAddOrUpdateVo);

    boolean add(RoleAddOrUpdateVo roleAddOrUpdateVo);

    void changeStatus(Long roleId,Long status);

}
