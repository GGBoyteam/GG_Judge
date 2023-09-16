package com.zhangsiyao.common.entity.system.dto;

import com.zhangsiyao.common.entity.system.dao.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPermissionDto implements Serializable {
    private User info;
    private List<String> permissions;
}
