package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AlgorithmTrueCodeQueryVo implements Serializable {

    @NotNull
    private Long pid;

    @NotNull
    @Min(1)
    private Long pageNum;

    @NotNull
    @Min(1)
    private Long pageSize;
}
