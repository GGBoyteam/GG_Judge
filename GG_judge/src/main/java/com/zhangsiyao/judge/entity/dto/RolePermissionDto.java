package com.zhangsiyao.judge.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhangsiyao.judge.entity.dao.Route;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RolePermissionDto implements Serializable {
    List<RouteTreeNode> menus;
    List<Long> checkedKeys;
}
