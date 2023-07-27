package com.zhangsiyao.auth.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordLoginVo implements Serializable {
    private String username;
    private String password;
}
