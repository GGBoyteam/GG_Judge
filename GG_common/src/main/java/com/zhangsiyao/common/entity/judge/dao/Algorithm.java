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
 * @author author
 * @since 2023-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("algorithm")
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 作者名称
     * */
    private String author;


    /**
     * 题目描述
     */
    private String description;

    /**
     * 输入描述
     */
    private String inputDescription;

    /**
     * 输出描述
     */
    private String outputDescription;

    /**
     * 样例描述
     */
    private String exampleDescription;

    /**
     * 是否展示(0展示，1不展示)
     */
    private Integer status;


}
