package com.zhangsiyao.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("compile_param")
public class CompileParam implements Serializable {
    @TableId
    private long id;

    private long compilerId;

    private long timeLimit;

    private long memoryLimit;
}
