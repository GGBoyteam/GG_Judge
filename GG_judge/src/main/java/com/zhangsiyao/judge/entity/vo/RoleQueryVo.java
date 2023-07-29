package com.zhangsiyao.judge.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleQueryVo implements Serializable {
    private Long pageNum;
    private Long pageSize;
    private String roleName;
    private String roleKey;
    private Integer status;
}
