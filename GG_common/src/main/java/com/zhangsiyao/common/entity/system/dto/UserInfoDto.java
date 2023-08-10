package com.zhangsiyao.common.entity.system.dto;

import com.zhangsiyao.common.entity.system.dao.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoDto extends UserInfo implements Serializable {
    private List<Long> roleIds;
}
