package com.zhangsiyao.common.entity.judge.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author iii
 */
@Data
public class AlgorithmQueryVo implements Serializable {

    private Long pid;

    private String title;

    @Min(0)
    @Max(1)
    private Integer status;

    @NotNull
    @Min(1)
    private Long pageSize;

    @NotNull
    @Min(1)
    private Long pageNum;

    private String author;

    private List<Long> tags;
}
