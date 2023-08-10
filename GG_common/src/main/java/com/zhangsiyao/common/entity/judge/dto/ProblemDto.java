package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.Example;
import com.zhangsiyao.common.entity.judge.dao.Problem;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemDto extends Problem implements Serializable {
    List<ProblemTag> tags;
    List<Example> examples;
}
