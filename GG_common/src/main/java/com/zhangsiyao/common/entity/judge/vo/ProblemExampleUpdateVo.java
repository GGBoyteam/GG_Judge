package com.zhangsiyao.common.entity.judge.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhangsiyao.common.entity.judge.dao.Example;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemExampleUpdateVo extends Example implements Serializable {
}
