package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemTrueCodeQueryVo implements Serializable {
    private Long pid;

    private Long pageNum;

    private Long pageSize;
}
