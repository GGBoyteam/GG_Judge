package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.vo.ProblemExampleSaveVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemExampleUpdateVo;
import com.zhangsiyao.judge.service.IExampleService;
import com.zhangsiyao.judge.service.IProblemCompileLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithmEdit")
public class AlgorithmEditController {

    @Autowired
    IProblemCompileLimitService compileLimitService;

    @Autowired
    IExampleService exampleService;

    /**
     * 添加编译器限制信息
     * */
    @PostMapping("/addCompileLimit")
    public R<String> addCompileLimit(@RequestParam("pid") Long pid,
                                     @RequestParam("language") String language,
                                     @RequestParam("time") Long time,
                                     @RequestParam("memory") Long memory){
        compileLimitService.saveCompilerLimit(pid,language,time,memory);
        return R.success();
    }

    /**
     * 更新编译器限制信息
     * */
    @PostMapping("/updateCompileLimit")
    public R<String> updateCompileLimit(@RequestParam("pid") Long pid,
                                        @RequestParam("id") Long id,
                                        @RequestParam("language") String language,
                                        @RequestParam("time") Long time,
                                        @RequestParam("memory") Long memory){
        compileLimitService.updateCompilerLimit(pid,id,language,time,memory);
        return R.success();
    }

    /**
     * 删除编译器限制信息
     * */
    @DeleteMapping("/deleteCompileLimit")
    public R<String> deleteCompileLimit(@RequestBody Long[] ids){
        compileLimitService.deleteCompilerLimit(ids);
        return R.success();
    }

    /**
     * 新增题目样例
     * */
    @PostMapping("/saveExample")
    public R<String> saveExample(@RequestBody ProblemExampleSaveVo saveVo,
                                 @RequestHeader("Authorization") String token){
        exampleService.saveExample(saveVo,token);
        return R.success();
    }

    /**
     * 更新题目样例
     * */
    @PostMapping("/updateExample")
    public R<String> updateExample(@RequestBody ProblemExampleUpdateVo updateVo,
                                   @RequestHeader("Authorization") String token){
        exampleService.updateExample(updateVo,token);
        return R.success();
    }

}
