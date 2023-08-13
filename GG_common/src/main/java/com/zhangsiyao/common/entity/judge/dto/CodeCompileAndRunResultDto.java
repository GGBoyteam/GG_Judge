package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.constant.Language;
import lombok.Data;

import java.io.Serializable;

@Data
public class CodeCompileAndRunResultDto implements Serializable {

    String status;
    String code;

    Language language;
    Integer version;

    String input;

    String output;

    Long time;

    Long memory;
}
