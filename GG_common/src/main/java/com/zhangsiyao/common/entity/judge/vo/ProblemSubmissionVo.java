package com.zhangsiyao.common.entity.judge.vo;

import com.zhangsiyao.common.constant.Language;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemSubmissionVo implements Serializable {
    private Long pid;
    private String code;
    private Language language;
    private String version;
}
