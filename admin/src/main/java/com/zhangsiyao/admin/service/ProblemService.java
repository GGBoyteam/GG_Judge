package com.zhangsiyao.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.Problem;
import com.zhangsiyao.common.result.ProblemInfo;

public interface ProblemService extends IService<Problem> {
    public Page<Problem> queryPage(long page,long pageSize,String key,Integer status);
}
