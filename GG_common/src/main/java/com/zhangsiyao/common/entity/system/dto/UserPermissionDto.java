package com.zhangsiyao.common.entity.system.dto;

import com.zhangsiyao.common.entity.system.dao.RolePermission;
import com.zhangsiyao.common.entity.system.dao.UserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPermissionDto implements Serializable {
    private UserInfo info;
    private List<String> permissions;
}
