package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.ProblemCompileLimit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemCompileLimitDto extends ProblemCompileLimit implements Serializable {
}
