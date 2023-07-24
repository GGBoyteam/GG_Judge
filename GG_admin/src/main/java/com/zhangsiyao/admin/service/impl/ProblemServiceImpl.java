package com.zhangsiyao.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.admin.mapper.ProblemMapper;
import com.zhangsiyao.common.entity.Problem;
import com.zhangsiyao.admin.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
    @Override
    public Page<Problem> queryPage(long page, long pageSize, String key, Integer status) {
        //分页参数
        Page<Problem> rowPage = new Page<>(page, pageSize);

        //queryWrapper组装查询where条件
        LambdaQueryWrapper<Problem> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(key)){
            queryWrapper=queryWrapper.like(Problem::getTitle,key);
        }
        if(status!=null){
            queryWrapper=queryWrapper.eq(Problem::getStatus,status);
        }
        rowPage = this.baseMapper.selectPage(rowPage, queryWrapper);
        return rowPage;
    }
}
