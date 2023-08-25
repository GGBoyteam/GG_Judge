package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmCompileLimitDto;
import com.zhangsiyao.judge.mapper.AlgorithmCompileLimitMapper;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-22
 */
@Service
public class AlgorithmCompileLimitServiceImpl extends ServiceImpl<AlgorithmCompileLimitMapper, AlgorithmCompileLimit> implements IAlgorithmCompileLimitService {

    @Override
    public IPage<AlgorithmCompileLimitDto> compilers(Long pid, Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlgorithmCompileLimit> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(AlgorithmCompileLimit::getPid,pid);
        return this.baseMapper.selectPage(new Page<>(pageNum,pageSize),queryWrapper).convert(limit->{
            AlgorithmCompileLimitDto problemCompileLimitDto=new AlgorithmCompileLimitDto();
            BeanUtils.copyProperties(limit,problemCompileLimitDto);
            return problemCompileLimitDto;
        });
    }

    @Override
    public void saveCompilerLimit(Long pid,String language, Long time, Long memory) {
        AlgorithmCompileLimit compileLimit=new AlgorithmCompileLimit();
        compileLimit.setPid(pid);
        compileLimit.setLanguage(language);
        compileLimit.setTime(time);
        compileLimit.setMemory(memory);
        this.save(compileLimit);
    }

    @Override
    public void updateCompilerLimit(Long pid, Long id, String language, Long time, Long memory) {
        AlgorithmCompileLimit compileLimit=new AlgorithmCompileLimit();
        compileLimit.setId(id);
        compileLimit.setPid(pid);
        compileLimit.setLanguage(language);
        compileLimit.setTime(time);
        compileLimit.setMemory(memory);
        this.updateById(compileLimit);
    }

    @Override
    public void deleteCompilerLimit(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
