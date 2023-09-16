package com.zhangsiyao.common.entity.auth.vo;

import com.zhangsiyao.common.entity.auth.dao.Auth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthAddOrUpdate extends Auth implements Serializable {
}
