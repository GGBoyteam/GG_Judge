package com.zhangsiyao.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("example")
public class Example implements Serializable {
    @TableId
    private long id;

    private long problemId;

    private String input;

    private String output;

    private long score;

    private int display;
}
