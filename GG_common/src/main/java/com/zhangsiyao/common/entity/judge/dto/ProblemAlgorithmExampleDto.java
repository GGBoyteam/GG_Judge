package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.AlgorithmExample;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemAlgorithmExampleDto extends AlgorithmExample implements Serializable {
}
