package com.zhangsiyao.common.entity.judge.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("algorithm_true_code")
public class AlgorithmTrueCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 正确代码id
     */
    @TableId(value = "code_id", type = IdType.AUTO)
    private Long codeId;

    /**
     * 题目id
     */
    private Long pid;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 编译器版本
     * */
    private String version;

    /**
     * 正确代码
     */
    private String code;


}
