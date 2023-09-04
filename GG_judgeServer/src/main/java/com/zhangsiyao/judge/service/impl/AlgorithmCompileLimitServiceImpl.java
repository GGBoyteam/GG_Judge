package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.Algorithm;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmCompileLimitDto;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmCompileLimitDeleteVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmCompileLimitQueryVo;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.AlgorithmCompileLimitMapper;
import com.zhangsiyao.judge.service.IAlgorithmCompileLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.judge.service.IAlgorithmService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IAlgorithmService algorithmService;

    @Override
    public IPage compileLimitList(AlgorithmCompileLimitQueryVo queryVo, String token) {
        Algorithm algorithm=algorithmService.getById(queryVo.getPid());
        algorithmService.checkAuthor(algorithm,token);
        LambdaQueryWrapper<AlgorithmCompileLimit> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(AlgorithmCompileLimit::getPid,queryVo.getPid());
        return this.baseMapper.selectPage(new Page<>(queryVo.getPageNum(),queryVo.getPageSize()),queryWrapper).convert(limit->{
            AlgorithmCompileLimitDto problemCompileLimitDto=new AlgorithmCompileLimitDto();
            BeanUtils.copyProperties(limit,problemCompileLimitDto);
            return problemCompileLimitDto;
        });
    }

    @SneakyThrows
    @Override
    public void saveCompilerLimit(AlgorithmCompileLimit compileLimit,String token) {
        Algorithm algorithm = algorithmService.getById(compileLimit.getPid());
        algorithmService.checkAuthor(algorithm,token);
        this.save(compileLimit);
    }

    @SneakyThrows
    @Override
    public void updateCompilerLimit(AlgorithmCompileLimit compileLimit,String token) {
        Algorithm algorithm = algorithmService.getById(compileLimit.getPid());
        algorithmService.checkAuthor(algorithm,token);
        this.updateById(compileLimit);
    }

    @SneakyThrows
    @Override
    public void deleteCompilerLimit(AlgorithmCompileLimitDeleteVo deleteVo, String token) {
        Algorithm algorithm = algorithmService.getById(deleteVo.getPid());
        algorithmService.checkAuthor(algorithm,token);
        this.removeByIds(deleteVo.getIds());
    }
}
