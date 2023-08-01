package com.zhangsiyao.common.entity.auth.vo;

import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginAddOrUpdate extends UserLogin implements Serializable {
}