package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.vo.ProblemBaseInfoUpdateVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemBodyUpdateVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemQueryVo;
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

    @GetMapping("/list")
    public R<Page<ProblemDto>> list(ProblemQueryVo queryVo, @RequestHeader("Authorization") String token){
        return R.success(problemService.list(queryVo,token));
    }

    @GetMapping("/info/{pid}")
    public R<ProblemDto> info(@PathVariable String pid,@RequestHeader("Authorization") String token){
        return R.success(problemService.info(pid));
    }

    @GetMapping("/tags")
    public R<List<ProblemTag>> tags(){
        return R.success(problemService.tags());
    }

    @PostMapping("/updateProblemBaseInfo")
    public R<String> updateProblemBaseInfo(@RequestBody ProblemBaseInfoUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.updateBaseInfo(updateVo);
        return R.success();
    }

    @PostMapping("/updateProblemBody")
    public R<String> updateProblemBody(@RequestBody ProblemBodyUpdateVo updateVo,@RequestHeader("Authorization") String token){
        problemService.updateProblemBody(updateVo);
        return R.success();
    }


}
