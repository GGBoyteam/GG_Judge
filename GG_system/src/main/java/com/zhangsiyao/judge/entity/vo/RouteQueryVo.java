package com.zhangsiyao.judge.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RouteQueryVo implements Serializable {
    private String name;

    private Integer status;
}
