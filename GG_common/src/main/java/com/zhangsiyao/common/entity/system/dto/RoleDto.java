package com.zhangsiyao.common.entity.system.dto;

import com.zhangsiyao.common.entity.system.dao.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends Role implements Serializable {
}
