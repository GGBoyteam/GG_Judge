package com.zhangsiyao.common.entity.judge.vo;

import com.zhangsiyao.common.constant.Language;
import lombok.Data;

import java.io.Serializable;

@Data
public class CodeCompileRunVo implements Serializable {
    private Language language;
    private Integer version;
    private String input;
    private String code;
}
