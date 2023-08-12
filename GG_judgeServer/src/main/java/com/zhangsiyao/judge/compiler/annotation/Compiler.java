package com.zhangsiyao.judge.compiler.annotation;

import com.zhangsiyao.common.constant.Language;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Compiler {
    Language language();

    String[] version();

}
