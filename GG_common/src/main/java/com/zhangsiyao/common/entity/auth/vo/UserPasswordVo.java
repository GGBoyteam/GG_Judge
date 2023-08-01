package com.zhangsiyao.common.entity.auth.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPasswordVo implements Serializable {
    private String username;
    private String password;
}
