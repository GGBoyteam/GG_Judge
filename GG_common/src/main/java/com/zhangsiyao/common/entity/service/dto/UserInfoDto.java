package com.zhangsiyao.common.entity.service.dto;

import com.zhangsiyao.common.entity.service.dao.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoDto extends UserInfo implements Serializable {
}
