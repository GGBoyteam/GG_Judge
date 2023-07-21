package com.zhangsiyao.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemInfo implements Serializable {
    private long id;

    private String key;

    private int status;
}
