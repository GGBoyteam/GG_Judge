package com.zhangsiyao.common.entity.service.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleAllocateUserVo implements Serializable {
    private Long pageNum;
    private Long pageSize;
    private String roleId;
    private Long phone;
    private String username;
}
