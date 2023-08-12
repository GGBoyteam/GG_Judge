package com.zhangsiyao.judge.service;

import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.common.entity.judge.dto.CompilerDto;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileRunVo;
import com.zhangsiyao.judge.compiler.JudgeResult;

import java.util.List;

public interface ICompilerService {

    JudgeResult compileAndRun(CodeCompileRunVo codeCompileRunVo);

    JudgeResult.Status compile(String code, Language language,Integer version);

    List<CompilerDto> compilerList();

}
