package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.judge.dao.*;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmDto;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemAlgorithmExampleDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemSubmissionResultDto;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.compiler.ICompiler;
import com.zhangsiyao.judge.exception.ExampleCheckException;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.AlgorithmMapper;
import com.zhangsiyao.judge.service.*;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements IAlgorithmService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    IAlgorithmExampleService exampleService;

    @Autowired
    IAlgorithmTagService tagService;

    @Autowired
    IAlgorithmTrueCodeService trueCodeService;

    @Autowired
    IAlgorithmTagRelationService tagRelationService;


    @Autowired
    ICompilerService compilerService;

    @SneakyThrows
    @Override
    public Page<AlgorithmDto> myAlgorithmProblems(AlgorithmQueryVo queryVo, String token) {
        String username= UserUtil.getUsernameByToken(redisTemplate,token);
        Page<Algorithm> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<Algorithm> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Algorithm::getAuthor,username);
        if(StringUtils.hasText(queryVo.getTitle())){
            queryWrapper=queryWrapper.like(Algorithm::getTitle,queryVo.getTitle());
        }
        if(queryVo.getStatus()!=null){
            queryWrapper=queryWrapper.eq(Algorithm::getStatus,queryVo.getStatus());
        }
        Page<Algorithm> problemPage = this.getBaseMapper().selectPage(page, queryWrapper);
        Page<AlgorithmDto> pages=new Page<>();
        List<AlgorithmDto> list=new ArrayList<>();
        problemPage.getRecords().forEach((problem -> {
            AlgorithmDto problemDto=new AlgorithmDto();
            BeanUtils.copyProperties(problem,problemDto);
            LambdaQueryWrapper<AlgorithmTagRelation> tagRelationQueryWrapper= new LambdaQueryWrapper<AlgorithmTagRelation>().eq(AlgorithmTagRelation::getPid,problemDto.getPid());
            Set<Long> tagIds=new HashSet<>();
            tagRelationService.getBaseMapper().selectList(tagRelationQueryWrapper).forEach(relation->{
                tagIds.add(relation.getTid());
            });
            if(tagIds.size()>0){
                LambdaQueryWrapper<AlgorithmTag> tagQueryWrapper=new LambdaQueryWrapper<AlgorithmTag>().in(AlgorithmTag::getTid,tagIds);
                problemDto.setTags(tagService.list(tagQueryWrapper));
            }
            list.add(problemDto);
        }));
        pages.setRecords(list);
        pages.setCountId(problemPage.getCountId());
        pages.setCurrent(problemPage.getCurrent());
        pages.setMaxLimit(problemPage.getMaxLimit());
        pages.setSize(problemPage.getSize());
        pages.setTotal(problemPage.getTotal());
        return pages;
    }

    @Override
    public Page<AlgorithmDto> listEnabled(AlgorithmQueryVo queryVo) {
        Page<Algorithm> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<Algorithm> queryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.hasText(queryVo.getTitle())){
            queryWrapper=queryWrapper.like(Algorithm::getTitle,queryVo.getTitle());
        }
        if(StringUtils.hasText(queryVo.getAuthor())){
            queryWrapper=queryWrapper.like(Algorithm::getAuthor,queryVo.getAuthor());
        }
        queryWrapper=queryWrapper.eq(Algorithm::getStatus,0);
        if(queryVo.getTags()!=null&&queryVo.getTags().size()>0){
            LambdaQueryWrapper<AlgorithmTagRelation> tagQueryWrapper=new LambdaQueryWrapper<>();
            tagQueryWrapper=tagQueryWrapper.in(AlgorithmTagRelation::getTid,queryVo.getTags());
            Set<Long> pids=new HashSet<>();
            tagRelationService.getBaseMapper().selectList(tagQueryWrapper).forEach(relaction->{
                pids.add(relaction.getPid());
            });
            if(pids.size()>0){
                queryWrapper=queryWrapper.in(Algorithm::getPid,pids);
            }else {
                return new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
            }
        }

        Page<Algorithm> problemPage = this.getBaseMapper().selectPage(page, queryWrapper);
        Page<AlgorithmDto> pages=new Page<>();
        List<AlgorithmDto> list=new ArrayList<>();
        problemPage.getRecords().forEach((problem -> {
            AlgorithmDto problemDto=new AlgorithmDto();
            BeanUtils.copyProperties(problem,problemDto);
            LambdaQueryWrapper<AlgorithmTagRelation> tagRelationQueryWrapper= new LambdaQueryWrapper<AlgorithmTagRelation>().eq(AlgorithmTagRelation::getPid,problemDto.getPid());
            Set<Long> tagIds=new HashSet<>();
            tagRelationService.getBaseMapper().selectList(tagRelationQueryWrapper).forEach(relation->{
                tagIds.add(relation.getTid());
            });
            if(tagIds.size()>0){
                LambdaQueryWrapper<AlgorithmTag> tagQueryWrapper=new LambdaQueryWrapper<AlgorithmTag>().in(AlgorithmTag::getTid,tagIds);
                problemDto.setTags(tagService.list(tagQueryWrapper));
            }
            list.add(problemDto);
        }));
        pages.setRecords(list);
        pages.setCountId(problemPage.getCountId());
        pages.setCurrent(problemPage.getCurrent());
        pages.setMaxLimit(problemPage.getMaxLimit());
        pages.setSize(problemPage.getSize());
        pages.setTotal(problemPage.getTotal());
        return pages;
    }

    @Override
    public Page<AlgorithmDto> listByAuthor(AlgorithmQueryVo queryVo) {
        return null;
    }

    @Override
    public AlgorithmDto info(String pid) {
        Algorithm algorithm = this.getById(pid);
        AlgorithmDto problemDto=new AlgorithmDto();
        BeanUtils.copyProperties(algorithm,problemDto);
        LambdaQueryWrapper<AlgorithmTagRelation> tagRelationQueryWrapper= new LambdaQueryWrapper<AlgorithmTagRelation>().eq(AlgorithmTagRelation::getPid,problemDto.getPid());
        Set<Long> tagIds=new HashSet<>();
        tagRelationService.getBaseMapper().selectList(tagRelationQueryWrapper).forEach(relation->{
            tagIds.add(relation.getTid());
        });
        if(tagIds.size()>0){
            LambdaQueryWrapper<AlgorithmTag> tagQueryWrapper=new LambdaQueryWrapper<AlgorithmTag>().in(AlgorithmTag::getTid,tagIds);
            problemDto.setTags(tagService.list(tagQueryWrapper));
        }
        LambdaQueryWrapper<AlgorithmExample> exampleQueryWrapper=new LambdaQueryWrapper<AlgorithmExample>().eq(AlgorithmExample::getPid,pid).eq(AlgorithmExample::getStatus,1);
        problemDto.setAlgorithmExamples(exampleService.list(exampleQueryWrapper));
        return problemDto;
    }

    @Override
    public AlgorithmDto infoIncludeAllExamples(String pid, String token) {
        return null;
    }

    @Override
    public List<AlgorithmTag> tags() {
        return tagService.list();
    }

    @SneakyThrows
    @Override
    public Page<AlgorithmTrueCode> trueCodeList(AlgorithmTrueCodeQueryVo queryVo, String token) {
        Algorithm algorithm =this.getById(queryVo.getPid());
        checkAuthor(algorithm,token);
        Page<AlgorithmTrueCode> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<AlgorithmTrueCode> queryWrapper = new LambdaQueryWrapper<AlgorithmTrueCode>().eq(AlgorithmTrueCode::getPid,queryVo.getPid());
        return trueCodeService.getBaseMapper().selectPage(page,queryWrapper);
    }

    @SneakyThrows
    @Override
    public Page<ProblemAlgorithmExampleDto> exampleList(AlgorithmExampleQueryVo queryVo, String token) {
        Algorithm algorithm =this.getById(queryVo.getPid());
        checkAuthor(algorithm,token);
        Page<AlgorithmExample> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<AlgorithmExample> queryWrapper=new LambdaQueryWrapper<AlgorithmExample>().eq(AlgorithmExample::getPid,queryVo.getPid());
        queryWrapper=queryWrapper.orderByDesc(AlgorithmExample::getStatus);
        Page<AlgorithmExample> examplePage = exampleService.getBaseMapper().selectPage(page, queryWrapper);
        return (Page<ProblemAlgorithmExampleDto>) examplePage.convert(example ->{
            ProblemAlgorithmExampleDto cur=new ProblemAlgorithmExampleDto();
            BeanUtils.copyProperties(example,cur);
            return cur;
        });
    }

    @Override
    @SneakyThrows
    public CodeCompileAndRunResultDto testExample(AlgorithmExampleTestVo testVo,String token) {
        Algorithm algorithm= this.getById(testVo.getPid());
        checkAuthor(algorithm,token);
        AlgorithmTrueCode code = trueCodeService.getById(testVo.getCodeId());
        if(code==null){
            throw new Exception("正确代码不存在");
        }
        if(!code.getPid().equals(algorithm.getPid())){
            throw new ExampleCheckException("题目没有id为"+testVo.getCodeId()+"的正确代码");
        }
        CodeCompileRunVo codeCompileRunVo=new CodeCompileRunVo();
        codeCompileRunVo.setCode(code.getCode());
        codeCompileRunVo.setLanguage(code.getLanguage());
        codeCompileRunVo.setVersion(code.getVersion());
        codeCompileRunVo.setInput(testVo.getInput());
        return compilerService.compileAndRun(codeCompileRunVo);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBaseInfo(AlgorithmBaseInfoUpdateVo updateVo,String token) {
        Algorithm algorithm = this.getById(updateVo.getPid());
        checkAuthor(algorithm,token);
        algorithm.setTitle(updateVo.getTitle());
        algorithm.setStatus(updateVo.getStatus());
        this.updateById(algorithm);
        LambdaQueryWrapper<AlgorithmTagRelation> queryWrapper=new LambdaQueryWrapper<AlgorithmTagRelation>().eq(AlgorithmTagRelation::getPid,updateVo.getPid());
        tagRelationService.getBaseMapper().delete(queryWrapper);
        List<AlgorithmTagRelation> tagRelations=new ArrayList<>();
        updateVo.getTags().forEach(tagId->{
            tagRelations.add(new AlgorithmTagRelation(null,updateVo.getPid(),tagId));
        });
        tagRelationService.saveBatch(tagRelations);
    }

    @SneakyThrows
    @Override
    public void updateBody(AlgorithmBodyUpdateVo updateVo, String token) {
        Algorithm algorithm = this.getById(updateVo.getPid());
        checkAuthor(algorithm,token);
        BeanUtils.copyProperties(updateVo, algorithm);
        this.updateById(algorithm);
    }

    @SneakyThrows
    @Override
    public boolean checkAuthor(Algorithm algorithm, String token) {
        if(algorithm==null){
            throw new Exception("题目不存在");
        }
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        return true;
    }


    @SneakyThrows
    @Override
    public ProblemSubmissionResultDto submission(ProblemSubmissionVo submissionVo) {
        ICompiler compiler=compilerService.compiler(submissionVo.getLanguage());
        LambdaQueryWrapper<AlgorithmExample> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(AlgorithmExample::getPid,submissionVo.getPid());
        List<AlgorithmExample> algorithmExamples =this.exampleService.getBaseMapper().selectList(queryWrapper);
        compiler.compile(submissionVo.getCode(),submissionVo.getVersion());
//        for(Example example:examples){
//            compiler.run(example.getInput(),);
//        }
        return null;
    }
}
