package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author iii
 */
@Data
public class AlgorithmQueryVo implements Serializable {
    private Long pid;
    private String title;
    private Integer status;
    private Long pageSize;
    private Long pageNum;
    private List<Long> tags;
}
