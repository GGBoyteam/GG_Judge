package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangsiyao.common.constant.Language;
import com.zhangsiyao.common.entity.judge.dao.Example;
import com.zhangsiyao.common.entity.judge.dao.Problem;
import com.zhangsiyao.common.entity.judge.dao.ProblemTrueCode;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.AnswerException;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.ExampleMapper;
import com.zhangsiyao.judge.service.ICompilerService;
import com.zhangsiyao.judge.service.IExampleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.judge.service.IProblemService;
import com.zhangsiyao.judge.service.IProblemTrueCodeService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
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
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements IExampleService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ICompilerService compilerService;

    @Autowired
    IProblemService problemService;

    @Autowired
    IProblemTrueCodeService problemTrueCodeService;


    @SneakyThrows
    @Override
    public void saveExample(Example example, String token) {
        Problem problem = problemService.getById(example.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        LambdaQueryWrapper<ProblemTrueCode> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(ProblemTrueCode::getPid,example.getPid());
        List<ProblemTrueCode> codes = problemTrueCodeService.getBaseMapper().selectList(queryWrapper);
        if(!example.getOutput().endsWith("\n")){
            example.setOutput(example.getOutput()+"\n");
        }
        for(ProblemTrueCode code:codes){
            JudgeResult result = compilerService.compileAndRun(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), example.getInput());
            if(!result.getOutput().equals(example.getOutput())){
                System.out.println("错误！！！！");
                throw new AnswerException(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), example.getInput(),example.getOutput(),result.getOutput());
            }
        }
        this.save(example);
    }

    @SneakyThrows
    @Override
    public void updateExample(Example example, String token) {
        Problem problem = problemService.getById(example.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        LambdaQueryWrapper<ProblemTrueCode> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(ProblemTrueCode::getPid,example.getPid());
        List<ProblemTrueCode> codes = problemTrueCodeService.getBaseMapper().selectList(queryWrapper);
        if(!example.getOutput().endsWith("\n")){
            example.setOutput(example.getOutput()+"\n");
        }
        for(ProblemTrueCode code:codes){
            JudgeResult result = compilerService.compileAndRun(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), example.getInput());
            if(!result.getOutput().equals(example.getOutput())){
                throw new AnswerException(Language.get(code.getLanguage()), code.getVersion(), code.getCode(), example.getInput(),example.getOutput(),result.getOutput());
            }
        }
        this.updateById(example);
    }
}
