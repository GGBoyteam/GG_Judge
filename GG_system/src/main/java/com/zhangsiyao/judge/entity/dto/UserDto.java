package com.zhangsiyao.judge.entity.dto;

import com.zhangsiyao.judge.entity.dao.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends UserInfo implements Serializable {
}
