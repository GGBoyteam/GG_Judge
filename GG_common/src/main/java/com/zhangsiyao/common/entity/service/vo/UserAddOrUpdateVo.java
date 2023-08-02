package com.zhangsiyao.common.entity.service.vo;

import com.zhangsiyao.common.entity.service.dao.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddOrUpdateVo extends UserInfo implements Serializable {
    private String password;
    private List<Long> roleIds;
}
