package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemBodyUpdateVo implements Serializable {

    private Long pid;

    /**
     * 题目描述
     */
    private String description;

    /**
     * 输入描述
     */
    private String inputDescription;

    /**
     * 输出描述
     */
    private String outputDescription;

    /**
     * 样例描述
     */
    private String exampleDescription;
}
