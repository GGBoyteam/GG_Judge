package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dao.ProblemTrueCode;
import com.zhangsiyao.common.entity.judge.dto.*;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.judge.service.IProblemCompileLimitService;
import com.zhangsiyao.judge.service.IProblemService;
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
    IProblemService problemService;

    @Autowired
    IProblemCompileLimitService problemCompileLimitService;

    @GetMapping("/list")
    public R<Page<ProblemDto>> list(ProblemQueryVo queryVo){
        return R.success(problemService.listAll(queryVo));
    }

    @GetMapping("/listByToken")
    public R<Page<ProblemDto>> listByToken(ProblemQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.listByToken(queryVo,token));
    }

    @GetMapping("/info/{pid}")
    public R<ProblemDto> info(@PathVariable String pid,@RequestHeader("Authorization") String token){
        return R.success(problemService.info(pid));
    }

    @GetMapping("/trueCodeListByToken")
    public R<Page<ProblemTrueCode>> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.trueCodeListByToken(queryVo,token));
    }

    @GetMapping("/examples")
    public R<Page<ProblemExampleDto>> examples(ProblemExampleQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.examples(queryVo,token));
    }

    @GetMapping("/compilerLimits")
    public R<IPage<ProblemCompileLimitDto>> compilerLimits(@RequestParam("pid") Long pid,
                                                           @RequestParam("pageNum") Long pageNum,
                                                           @RequestParam("pageSize") Long pageSize){
        return R.success(problemCompileLimitService.compilers(pid,pageNum,pageSize));
    }

    @PostMapping("/testExample")
    public R<CodeCompileAndRunResultDto> testExample(@RequestBody ProblemExampleTestVo testVo){
        return R.success(problemService.testExample(testVo));
    }

    @PostMapping("/saveOrUpdateExample")
    public R<String> saveOrUpdateExample(@RequestBody ProblemExampleUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.saveOrUpdateProblemExample(updateVo,token);
        return R.success();
    }

    @GetMapping("/tags")
    public R<List<ProblemTag>> tags(){
        return R.success(problemService.tags());
    }

    @PostMapping("/updateProblemBaseInfo")
    public R<String> updateProblemBaseInfo(@RequestBody ProblemBaseInfoUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.updateBaseInfo(updateVo,token);
        return R.success();
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

    @PostMapping("/saveOrUpdateProblemExample")
    public R<String> saveOrUpdateProblemExample(@RequestBody ProblemExampleUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.saveOrUpdateProblemExample(updateVo,token);
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
