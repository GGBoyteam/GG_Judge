package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.entity.judge.dao.Example;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemExampleDto extends Example implements Serializable {
}
