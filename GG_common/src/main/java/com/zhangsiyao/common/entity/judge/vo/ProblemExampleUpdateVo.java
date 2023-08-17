package com.zhangsiyao.common.entity.judge.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhangsiyao.common.entity.judge.dao.Example;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class ProblemExampleUpdateVo implements Serializable {
    /**
     * 样例id
     */
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
    private Integer status;
}
