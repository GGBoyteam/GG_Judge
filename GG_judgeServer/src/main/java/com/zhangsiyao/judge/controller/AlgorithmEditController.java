package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.judge.service.IAlgorithmExampleService;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import com.zhangsiyao.judge.service.IAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/algorithmEdit")
public class AlgorithmEditController {

    @Autowired
    IAlgorithmCompileLimitService compileLimitService;

    @Autowired
    IAlgorithmExampleService exampleService;

    @Autowired
    IAlgorithmService algorithmService;

    /**
     * 更新题目基本信息
     * */
    @PostMapping("/updateBaseInfo")
    public R<String> updateBaseInfo(@RequestBody AlgorithmBaseInfoUpdateVo updateVo,
                                    @RequestHeader("Authorization") String token){
        algorithmService.updateBaseInfo(updateVo,token);
        return R.success();
    }


    /**
     * 添加编译器限制信息
     * */
    @PostMapping("/addCompileLimit")
    public R<String> addCompileLimit(@RequestBody AlgorithmCompileLimitSaveVo saveVo,
                                     @RequestHeader("Authorization") String token){
        compileLimitService.saveCompilerLimit(saveVo,token);
        return R.success();
    }

    /**
     * 更新编译器限制信息
     * */
    @PostMapping("/updateCompileLimit")
    public R<String> updateCompileLimit(@RequestBody AlgorithmCompileLimitUpdateVo updateVo,
                                        @RequestHeader("Authorization") String token){
        compileLimitService.updateCompilerLimit(updateVo,token);
        return R.success();
    }

    /**
     * 删除编译器限制信息
     * */
    @DeleteMapping("/deleteCompileLimit")
    public R<String> deleteCompileLimit(@RequestBody AlgorithmCompileLimitDeleteVo deleteVo,
                                        @RequestHeader("Authorization") String token){
        compileLimitService.deleteCompilerLimit(deleteVo,token);
        return R.success();
    }

    /**
     * 新增题目样例
     * */
    @PostMapping("/saveExample")
    public R<String> saveExample(@RequestBody AlgorithmExampleSaveVo saveVo,
                                 @RequestHeader("Authorization") String token){
        exampleService.saveExample(saveVo,token);
        return R.success();
    }

    /**
     * 更新题目样例
     * */
    @PostMapping("/updateExample")
    public R<String> updateExample(@RequestBody AlgorithmExampleUpdateVo updateVo,
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
