package com.zhangsiyao.auth.entity.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("role_permission")
public class PermissionDto implements Serializable {
    @TableId
    private long id;

    private long roleId;

    private String permission;
}
