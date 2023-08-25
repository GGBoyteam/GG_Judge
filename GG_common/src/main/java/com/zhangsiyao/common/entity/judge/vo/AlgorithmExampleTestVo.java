package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlgorithmExampleTestVo implements Serializable {
    private Long codeId;
    private String input;
    private String output;
}
