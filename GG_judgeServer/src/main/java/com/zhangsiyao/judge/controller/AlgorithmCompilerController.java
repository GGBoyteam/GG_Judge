package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.CompilerDto;
import com.zhangsiyao.common.entity.judge.vo.CodeCompileRunVo;
import com.zhangsiyao.judge.service.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compiler")
public class AlgorithmCompilerController {

    @Autowired
    ICompilerService compilerService;

    @PostMapping("/compileRun")
    public R<CodeCompileAndRunResultDto> compileRun(@RequestBody CodeCompileRunVo codeCompileRun){
        System.out.println(codeCompileRun);
        return R.success(compilerService.compileAndRun(codeCompileRun));
    }

    @GetMapping("/list")
    public R<List<CompilerDto>> list(){
        return R.success(compilerService.compilerList());
    }
}
