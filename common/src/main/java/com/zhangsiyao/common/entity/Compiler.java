package com.zhangsiyao.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("compiler")
public class Compiler implements Serializable {
    @TableId
    private long id;

    private String name;

    private String arg;

    private String env;

}
