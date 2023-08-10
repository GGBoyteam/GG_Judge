package com.zhangsiyao.common.entity.judge.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@TableName("problem_tag_relation")
public class ProblemTagRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目标签关联id
     */
    private Long rid;

    /**
     * 题目id
     */
    private Long pid;

    /**
     * 标签id
     */
    private Long tid;


}
