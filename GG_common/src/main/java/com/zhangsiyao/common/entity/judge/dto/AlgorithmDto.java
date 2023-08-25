package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.Algorithm;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmExample;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlgorithmDto extends Algorithm implements Serializable {
    List<AlgorithmTag> tags;
    List<AlgorithmExample> algorithmExamples;
}
