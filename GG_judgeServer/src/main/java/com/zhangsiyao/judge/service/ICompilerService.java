package com.zhangsiyao.judge.service;

import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.CompilerDto;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileRunVo;
import com.zhangsiyao.judge.compiler.JudgeResult;

import java.util.List;

public interface ICompilerService {

    CodeCompileAndRunResultDto compileAndRun(CodeCompileRunVo codeCompileRunVo);

    JudgeResult compileAndRun(Language language,String version,String code,String input);

    JudgeResult.Status compile(String code, Language language,String version);

    List<CompilerDto> compilerList();

}
