package com.zhangsiyao.common.entity.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RolePermissionDto implements Serializable {
    List<RouteTreeNode> menus;
    List<Long> checkedKeys;
}
