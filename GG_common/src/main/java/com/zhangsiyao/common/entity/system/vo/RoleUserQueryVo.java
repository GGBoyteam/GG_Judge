package com.zhangsiyao.common.entity.system.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleUserQueryVo implements Serializable {
    private Long pageNum;

    private Long pageSize;

    private Long roleId;

    private Long userId;
}
