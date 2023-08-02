package com.zhangsiyao.common.entity.service.dto;

import com.zhangsiyao.common.entity.service.dao.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends Role implements Serializable {
}
