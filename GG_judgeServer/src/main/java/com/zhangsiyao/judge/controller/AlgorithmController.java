package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import com.zhangsiyao.judge.service.IAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public R list(@Valid AlgorithmQueryVo queryVo){
        return R.success(algorithmService.listEnabled(queryVo));
    }

    /**
     * 分页获取出题人为当前账户用户的算法题目列表
     * */
    @GetMapping("/myAlgorithmProblems")
    public R listByToken(@Valid AlgorithmQueryVo queryVo,
                         @RequestHeader("Authorization") String token){
        return R.success(algorithmService.myAlgorithmProblems(queryVo,token));
    }

    /**
     * 获取完整的算法题目信息
     * */
    @GetMapping("/info")
    public R info(@RequestParam("pid") String pid){
        return R.success(algorithmService.info(pid));
    }


    /**
     * 分页获取正确代码列表
     * */
    @GetMapping("/trueCodeList")
    public R trueCodeList(@Valid AlgorithmTrueCodeQueryVo queryVo,
                          @RequestHeader("Authorization") String token){
        return R.success(algorithmService.trueCodeList(queryVo,token));
    }

    /**
     * 分页获取样例列表
     * */
    @GetMapping("/exampleList")
    public R examples(@Valid AlgorithmExampleQueryVo queryVo,
                      @RequestHeader("Authorization") String token){
        return R.success(algorithmService.exampleList(queryVo,token));
    }

    /**
     * 分页获取编译器限制列表
     * */
    @GetMapping("/compilerLimitList")
    public R compilerLimitsList(@Valid AlgorithmCompileLimitQueryVo queryVo,
                                @RequestHeader("Authorization") String token){
        return R.success(compileLimitService.compileLimitList(queryVo,token));
    }


    /**
     * 获取算法题目标签
     * */
    @GetMapping("/tags")
    public R tags(){
        return R.success(algorithmService.tags());
    }


    /**
     * 算法样例测试运行
     * */
    @PostMapping("/testExample")
    public R test(@RequestBody AlgorithmExampleTestVo testVo,
                  @RequestHeader("Authorization") String token){
        return R.success(algorithmService.testExample(testVo,token));
    }

    
    /**
     * 代码提交运行
     * */
    @PostMapping("/submission")
    public R submission(@RequestBody ProblemSubmissionVo problemSubmissionVo){
        return R.success(algorithmService.submission(problemSubmissionVo));
    }

}
