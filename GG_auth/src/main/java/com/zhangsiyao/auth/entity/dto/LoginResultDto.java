package com.zhangsiyao.auth.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResultDto implements Serializable {
    private String message;
    private String token;
}
