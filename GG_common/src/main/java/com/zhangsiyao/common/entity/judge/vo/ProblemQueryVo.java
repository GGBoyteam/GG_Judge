package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author iii
 */
@Data
public class ProblemQueryVo implements Serializable {
    private Long pid;
    private String title;
    private Integer status;
    private Long pageSize;
    private Long pageNum;
}
