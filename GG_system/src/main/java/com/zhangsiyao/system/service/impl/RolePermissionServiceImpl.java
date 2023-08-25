package com.zhangsiyao.system.service.impl;

import com.zhangsiyao.common.entity.system.dao.RolePermission;
import com.zhangsiyao.common.service.feign.JudgeService;
import com.zhangsiyao.common.utils.PermissionUtil;
import com.zhangsiyao.system.mapper.RolePermissionMapper;
import com.zhangsiyao.system.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Autowired
    JudgeService judgeService;

    @Override
    public List<String> permissions() {
        Set<String> permission= PermissionUtil.getPermissions();
        permission.addAll(judgeService.permissionList());
        return new ArrayList<>(permission);
    }
}
