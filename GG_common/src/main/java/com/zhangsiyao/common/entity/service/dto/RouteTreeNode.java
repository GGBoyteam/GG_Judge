package com.zhangsiyao.common.entity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RouteTreeNode implements Serializable {
    private Long id;
    private Long parent;
    private String title;

    private List<RouteTreeNode> children;
}
