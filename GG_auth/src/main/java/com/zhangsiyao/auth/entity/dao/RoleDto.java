package com.zhangsiyao.auth.entity.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("role")
public class RoleDto implements Serializable {
    @TableId
    private long id;

    private String name;

    private String description;
}
