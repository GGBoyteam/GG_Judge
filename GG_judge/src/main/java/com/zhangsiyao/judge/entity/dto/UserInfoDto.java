package com.zhangsiyao.judge.entity.dto;

import com.zhangsiyao.judge.entity.dao.RolePermission;
import com.zhangsiyao.judge.entity.dao.UserInfo;
import lombok.Data;

import java.io.PipedReader;
import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoDto implements Serializable {
    private UserInfo info;
    private List<RolePermission> permissions;
}
