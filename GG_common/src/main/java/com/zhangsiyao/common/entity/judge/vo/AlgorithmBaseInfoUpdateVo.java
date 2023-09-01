package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AlgorithmBaseInfoUpdateVo implements Serializable {
    private Long pid;
    private String title;
    private Integer status;
    private List<Long> tags;
}
