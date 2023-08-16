package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemExampleUpdateVo implements Serializable {
    private Long trueCodeId;
    private String input;
    private String output;
}
