package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AlgorithmCompileLimitQueryVo implements Serializable {

    @NotNull
    Long pid;

    @NotNull
    @Min(1)
    Long pageNum;

    @NotNull
    @Min(1)
    Long pageSize;


}
