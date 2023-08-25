package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmExampleDeleteVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmAlgorithmExampleSaveVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmAlgorithmExampleUpdateVo;
import com.zhangsiyao.judge.service.IAlgorithmExampleService;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithmEdit")
public class AlgorithmEditController {

    @Autowired
    IAlgorithmCompileLimitService compileLimitService;

    @Autowired
    IAlgorithmExampleService exampleService;

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
    public R<String> saveExample(@RequestBody AlgorithmAlgorithmExampleSaveVo saveVo,
                                 @RequestHeader("Authorization") String token){
        exampleService.saveExample(saveVo,token);
        return R.success();
    }

    /**
     * 更新题目样例
     * */
    @PostMapping("/updateExample")
    public R<String> updateExample(@RequestBody AlgorithmAlgorithmExampleUpdateVo updateVo,
                                   @RequestHeader("Authorization") String token){
        exampleService.updateExample(updateVo,token);
        return R.success();
    }

    /**
     * 删除题目样例
     * */
    @DeleteMapping("/deleteExample")
    public R<String> deleteExample(@RequestBody AlgorithmExampleDeleteVo deleteVo,
                                   @RequestHeader("Authorization") String token){
        exampleService.deleteExample(deleteVo.getPid(),deleteVo.getIds(),token);
        return R.success();
    }

}
