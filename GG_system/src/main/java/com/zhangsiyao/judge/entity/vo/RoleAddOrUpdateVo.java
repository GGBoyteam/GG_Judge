package com.zhangsiyao.judge.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAddOrUpdateVo implements Serializable {
    private Long id;

    private String name;

    private String roleKey;

    private List<Long> routeIds;

    private Long sort;

    private Integer status;
}
