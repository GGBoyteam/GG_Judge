package com.zhangsiyao.system.service;

import com.zhangsiyao.common.entity.system.dao.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
public interface IRolePermissionService extends IService<RolePermission> {
    List<String> permissions();
}
