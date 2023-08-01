package com.zhangsiyao.common.entity.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResultDto implements Serializable {
    private String token;
    private Long empireTime;
}
