package com.zhangsiyao.common.entity.service.dto;

import com.zhangsiyao.common.entity.service.dao.RolePermission;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPermissionDto implements Serializable {
    private UserInfo info;
    private List<RolePermission> permissions;
}
