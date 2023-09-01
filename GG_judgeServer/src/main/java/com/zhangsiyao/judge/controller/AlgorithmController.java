package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTag;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTrueCode;
import com.zhangsiyao.common.entity.judge.dto.*;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import com.zhangsiyao.judge.service.IAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author iii
 */
@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

    @Autowired
    IAlgorithmService problemService;

    @Autowired
    IAlgorithmCompileLimitService problemCompileLimitService;

    @GetMapping("/list")
    public R<Page<AlgorithmDto>> list(ProblemQueryVo queryVo){
        return R.success(problemService.listAll(queryVo));
    }

    @GetMapping("/listByToken")
    public R<Page<AlgorithmDto>> listByToken(ProblemQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.listByToken(queryVo,token));
    }

    @GetMapping("/info/{pid}")
    public R<AlgorithmDto> info(@PathVariable String pid, @RequestHeader("Authorization") String token){
        return R.success(problemService.info(pid));
    }

    @GetMapping("/trueCodeListByToken")
    public R<Page<AlgorithmTrueCode>> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.trueCodeListByToken(queryVo,token));
    }

    @GetMapping("/examples")
    public R<Page<ProblemAlgorithmExampleDto>> examples(AlgorithmExampleQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.examples(queryVo,token));
    }

    @GetMapping("/compilerLimits")
    public R<IPage<AlgorithmCompileLimitDto>> compilerLimits(@RequestParam("pid") Long pid,
                                                             @RequestParam("pageNum") Long pageNum,
                                                             @RequestParam("pageSize") Long pageSize){
        return R.success(problemCompileLimitService.compilers(pid,pageNum,pageSize));
    }

    @PostMapping("/testExample")
    public R<CodeCompileAndRunResultDto> testExample(@RequestBody AlgorithmExampleTestVo testVo){
        return R.success(problemService.testExample(testVo));
    }


    @GetMapping("/tags")
    public R<List<AlgorithmTag>> tags(){
        return R.success(problemService.tags());
    }


    @PostMapping("/updateProblemBody")
    public R<String> updateProblemBody(@RequestBody ProblemBodyUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.updateProblemBody(updateVo,token);
        return R.success();
    }

    @PostMapping("/saveOrUpdateProblemTrueCode")
    public R<String> saveOrUpdateProblemTrueCode(@RequestBody ProblemTrueCodeUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.saveOrUpdateProblemTrueCode(updateVo,token);
        return R.success();
    }


    @PostMapping("/submission")
    public R<ProblemSubmissionResultDto> submission(@RequestBody ProblemSubmissionVo problemSubmissionVo){
        return R.success(problemService.submission(problemSubmissionVo));
    }

    @DeleteMapping("/deleteTrueCode/{codeId}")
    public R<String> deleteTrueCode(@PathVariable String codeId){
        problemService.deleteTrueCode(codeId);
        return R.success();
    }


}
