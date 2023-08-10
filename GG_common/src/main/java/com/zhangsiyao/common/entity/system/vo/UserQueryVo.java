package com.zhangsiyao.common.entity.system.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryVo implements Serializable {
    private Long pageNum;
    private Long pageSize;
    private String username;
    private Long  phone;
    private Integer status;
}
