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
    IAlgorithmService algorithmService;

    @Autowired
    IAlgorithmCompileLimitService compileLimitService;

    /**
     * 分页获取算法题目列表(启用的题目)
     * */
    @GetMapping("/listEnabled")
    public R<Page<AlgorithmDto>> list(AlgorithmQueryVo queryVo){
        return R.success(algorithmService.listEnabled(queryVo));
    }

    /**
     * 分页获取出题人为当前账户用户的算法题目列表
     * */
    @GetMapping("/listByToken")
    public R<Page<AlgorithmDto>> listByToken(AlgorithmQueryVo queryVo,
                                             @RequestHeader("Authorization") String token){
        return R.success(algorithmService.listByToken(queryVo,token));
    }

    /**
     * 获取算法题目信息
     * */
    @GetMapping("/info/{pid}")
    public R<AlgorithmDto> info(@PathVariable String pid){
        return R.success(algorithmService.info(pid));
    }


    @GetMapping("/trueCodeListByToken")
    public R<Page<AlgorithmTrueCode>> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(algorithmService.trueCodeListByToken(queryVo,token));
    }

    @GetMapping("/examples")
    public R<Page<ProblemAlgorithmExampleDto>> examples(AlgorithmExampleQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(algorithmService.examples(queryVo,token));
    }

    @GetMapping("/compilerLimits")
    public R<IPage<AlgorithmCompileLimitDto>> compilerLimits(@RequestParam("pid") Long pid,
                                                             @RequestParam("pageNum") Long pageNum,
                                                             @RequestParam("pageSize") Long pageSize){
        return R.success(compileLimitService.compilers(pid,pageNum,pageSize));
    }

    @PostMapping("/testExample")
    public R<CodeCompileAndRunResultDto> testExample(@RequestBody AlgorithmExampleTestVo testVo){
        return R.success(algorithmService.testExample(testVo));
    }

    @GetMapping("/tags")
    public R<List<AlgorithmTag>> tags(){
        return R.success(algorithmService.tags());
    }


    @PostMapping("/submission")
    public R<ProblemSubmissionResultDto> submission(@RequestBody ProblemSubmissionVo problemSubmissionVo){
        return R.success(algorithmService.submission(problemSubmissionVo));
    }

}
