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
@TableName("example")
public class Example implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 样例id
     */
    @TableId(value = "eid", type = IdType.AUTO)
    private Long eid;

    /**
     * 题目id
     */
    private Long pid;

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

    /**
     * 是否作为题目展示的样例(1展示,0作为判题样例)
     */
    private Boolean status;


}
