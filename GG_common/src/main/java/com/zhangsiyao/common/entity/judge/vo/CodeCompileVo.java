package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeCompileVo implements Serializable {
    private String language;
    private Integer version;
    private String code;
}
