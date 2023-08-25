package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlgorithmCompileLimitDto extends AlgorithmCompileLimit implements Serializable {
}
