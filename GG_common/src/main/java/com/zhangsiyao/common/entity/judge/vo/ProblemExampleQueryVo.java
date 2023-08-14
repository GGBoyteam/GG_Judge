package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemExampleQueryVo implements Serializable {
    Long pid;
    Long pageNum;
    Long pageSize;
}
