package com.zhangsiyao.common.entity.system.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RouteQueryVo implements Serializable {
    private String name;

    private Integer status;
}
