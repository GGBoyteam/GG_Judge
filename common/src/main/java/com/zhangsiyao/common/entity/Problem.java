package com.zhangsiyao.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "problem")
@Data
public class Problem implements Serializable {
    @TableId
    private long id;

    private String title;

    private String description;

    private String inputDescription;

    private String outputDescription;

    private String dataDescription;

    private int status;
}
