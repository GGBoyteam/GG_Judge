package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dto.CompilerDto;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileRunVo;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compiler")
public class CompilerController {

    @Autowired
    ICompilerService compilerService;

    @PostMapping("/compileRun")
    public R<JudgeResult> compileRun(@RequestBody CodeCompileRunVo codeCompileRun){
        return R.success(compilerService.compileAndRun(codeCompileRun));
    }

    @GetMapping("/list")
    public R<List<CompilerDto>> list(){
        return R.success(compilerService.compilerList());
    }
}
