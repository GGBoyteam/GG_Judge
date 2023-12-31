package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AlgorithmExampleTestVo implements Serializable {

    @NotNull
    private Long pid;

    @NotNull
    private Long codeId;

    private String input;

    private String output;
}
