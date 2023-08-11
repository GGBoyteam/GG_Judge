package com.zhangsiyao.judge.service;

import com.zhangsiyao.common.entity.judge.vo.CodeCompileVo;
import com.zhangsiyao.judge.compiler.JudgeResult;

public interface ICompilerService {

    JudgeResult compile(CodeCompileVo codeCompileVo);


}
