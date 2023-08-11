package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileVo;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compiler")
public class CompilerController {

    @Autowired
    ICompilerService compilerService;
    @PostMapping("/compile")
    public R<JudgeResult> compile(@RequestBody CodeCompileVo codeCompileVo) {
        return R.success(compilerService.compile(codeCompileVo));
    }



}
