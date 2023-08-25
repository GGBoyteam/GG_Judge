package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AlgorithmExampleDeleteVo implements Serializable {

    Long pid;

    List<Long> ids;
}
