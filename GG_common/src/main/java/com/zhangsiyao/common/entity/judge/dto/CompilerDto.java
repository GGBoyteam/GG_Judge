package com.zhangsiyao.common.entity.judge.dto;

import com.zhangsiyao.common.constant.Language;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CompilerDto implements Serializable {

    Language language;

    List<String> version;

}
