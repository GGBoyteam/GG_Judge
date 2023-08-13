package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dao.ProblemTrueCode;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.judge.service.IProblemService;
import com.zhangsiyao.judge.service.IProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author iii
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    IProblemService problemService;

    @GetMapping("/listByToken")
    public R<Page<ProblemDto>> list(ProblemQueryVo queryVo, @RequestHeader("Authorization") String token){
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

    @DeleteMapping("/deleteTrueCode/{codeId}")
    public R<String> deleteTrueCode(@PathVariable String codeId){
        problemService.deleteTrueCode(codeId);
        return R.success();
    }


}
