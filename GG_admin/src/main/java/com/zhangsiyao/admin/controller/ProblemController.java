package com.zhangsiyao.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.admin.service.ProblemService;
import com.zhangsiyao.common.entity.Problem;
import com.zhangsiyao.common.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

    @Autowired
    ProblemService problemService;
    @GetMapping("/list")
    public R<Page<Problem>> list(long page, long pageSize, String key, Integer status){
        System.out.println(page+pageSize+key+status);
        return R.success(problemService.queryPage(page,pageSize,key,status));
    }
}
