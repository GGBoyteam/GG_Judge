package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.common.entity.judge.dao.Algorithm;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmExample;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTrueCode;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.AnswerException;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.ExampleMapper;
import com.zhangsiyao.judge.service.ICompilerService;
import com.zhangsiyao.judge.service.IAlgorithmExampleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.judge.service.IAlgorithmService;
import com.zhangsiyao.judge.service.IAlgorithmTrueCodeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
@Service
@SuppressWarnings("all")
public class AlgorithmExampleServiceImpl extends ServiceImpl<ExampleMapper, AlgorithmExample> implements IAlgorithmExampleService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ICompilerService compilerService;

    @Autowired
    IAlgorithmService problemService;

    @Autowired
    IAlgorithmTrueCodeService problemTrueCodeService;


    @SneakyThrows
    @Override
    public void saveExample(AlgorithmExample algorithmExample, String token) {
        Algorithm algorithm = problemService.getById(algorithmExample.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        LambdaQueryWrapper<AlgorithmTrueCode> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(AlgorithmTrueCode::getPid, algorithmExample.getPid());
        List<AlgorithmTrueCode> codes = problemTrueCodeService.getBaseMapper().selectList(queryWrapper);
        if(!algorithmExample.getOutput().endsWith("\n")){
            algorithmExample.setOutput(algorithmExample.getOutput()+"\n");
        }
        for(AlgorithmTrueCode code:codes){
            JudgeResult result = compilerService.compileAndRun(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), algorithmExample.getInput());
            if(!result.getOutput().equals(algorithmExample.getOutput())){
                throw new AnswerException(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), algorithmExample.getInput(), algorithmExample.getOutput(),result.getOutput());
            }
        }
        this.save(algorithmExample);
    }

    @SneakyThrows
    @Override
    public void updateExample(AlgorithmExample algorithmExample, String token) {
        Algorithm algorithm = problemService.getById(algorithmExample.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        LambdaQueryWrapper<AlgorithmTrueCode> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(AlgorithmTrueCode::getPid, algorithmExample.getPid());
        List<AlgorithmTrueCode> codes = problemTrueCodeService.getBaseMapper().selectList(queryWrapper);
        if(!algorithmExample.getOutput().endsWith("\n")){
            algorithmExample.setOutput(algorithmExample.getOutput()+"\n");
        }
        for(AlgorithmTrueCode code:codes){
            JudgeResult result = compilerService.compileAndRun(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), algorithmExample.getInput());
            if(!result.getOutput().equals(algorithmExample.getOutput())){
                System.out.println("错误！！！！");
                throw new AnswerException(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), algorithmExample.getInput(), algorithmExample.getOutput(),result.getOutput());
            }
        }
        this.updateById(algorithmExample);
    }

    @SneakyThrows
    @Override
    public void deleteExample(Long pid,List<Long> ids, String token) {
        Algorithm algorithm = problemService.getById(pid);
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        this.removeByIds(ids);
    }


}
