package com.zhangsiyao.common.entity.system.dto;

import com.zhangsiyao.common.entity.system.dao.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends User implements Serializable {
    private List<Long> roleIds;
}
