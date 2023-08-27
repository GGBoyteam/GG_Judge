package com.zhangsiyao.common.entity.judge.vo;

import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlgorithmCompileLimitUpdateVo extends AlgorithmCompileLimit implements Serializable {
}
