package com.zhangsiyao.judge.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import com.zhangsiyao.common.entity.judge.dao.*;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.vo.*;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.CodeCompileException;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.ProblemMapper;
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
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    IExampleService exampleService;

    @Autowired
    IProblemTagService tagService;

    @Autowired
    IProblemTrueCodeService trueCodeService;

    @Autowired
    IProblemTagRelationService tagRelationService;

    @Autowired
    IProblemTrueCodeService problemTrueCodeService;

    @Autowired
    ICompilerService compilerService;

    @SneakyThrows
    @Override
    public Page<ProblemDto> listByToken(ProblemQueryVo queryVo,String token) {
        String username= UserUtil.getUsernameByToken(redisTemplate,token);
        Page<Problem> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<Problem> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Problem::getAuthor,username);
        if(StringUtils.hasText(queryVo.getTitle())){
            queryWrapper=queryWrapper.like(Problem::getTitle,queryVo.getTitle());
        }
        if(queryVo.getStatus()!=null){
            queryWrapper=queryWrapper.eq(Problem::getStatus,queryVo.getStatus());
        }
        Page<Problem> problemPage = this.getBaseMapper().selectPage(page, queryWrapper);
        Page<ProblemDto> pages=new Page<>();
        List<ProblemDto> list=new ArrayList<>();
        problemPage.getRecords().forEach((problem -> {
            ProblemDto problemDto=new ProblemDto();
            BeanUtils.copyProperties(problem,problemDto);
            LambdaQueryWrapper<ProblemTagRelation> tagRelationQueryWrapper= new LambdaQueryWrapper<ProblemTagRelation>().eq(ProblemTagRelation::getPid,problemDto.getPid());
            Set<Long> tagIds=new HashSet<>();
            tagRelationService.getBaseMapper().selectList(tagRelationQueryWrapper).forEach(relation->{
                tagIds.add(relation.getTid());
            });
            if(tagIds.size()>0){
                LambdaQueryWrapper<ProblemTag> tagQueryWrapper=new LambdaQueryWrapper<ProblemTag>().in(ProblemTag::getTid,tagIds);
                problemDto.setTags(tagService.list(tagQueryWrapper));
            }
            list.add(problemDto);
        }));
        System.out.println(problemPage.getTotal());
        pages.setRecords(list);
        pages.setCountId(problemPage.getCountId());
        pages.setCurrent(problemPage.getCurrent());
        pages.setMaxLimit(problemPage.getMaxLimit());
        pages.setSize(problemPage.getSize());
        pages.setTotal(problemPage.getTotal());
        return pages;
    }

    @Override
    public Page<ProblemDto> listAll(ProblemQueryVo queryVo) {
        return null;
    }

    @Override
    public Page<ProblemDto> listByAuthor(ProblemQueryVo queryVo) {
        return null;
    }

    @Override
    public ProblemDto info(String pid) {
        Problem problem = this.getById(pid);
        ProblemDto problemDto=new ProblemDto();
        BeanUtils.copyProperties(problem,problemDto);
        LambdaQueryWrapper<ProblemTagRelation> tagRelationQueryWrapper= new LambdaQueryWrapper<ProblemTagRelation>().eq(ProblemTagRelation::getPid,problemDto.getPid());
        Set<Long> tagIds=new HashSet<>();
        tagRelationService.getBaseMapper().selectList(tagRelationQueryWrapper).forEach(relation->{
            tagIds.add(relation.getTid());
        });
        if(tagIds.size()>0){
            LambdaQueryWrapper<ProblemTag> tagQueryWrapper=new LambdaQueryWrapper<ProblemTag>().in(ProblemTag::getTid,tagIds);
            problemDto.setTags(tagService.list(tagQueryWrapper));
        }
        LambdaQueryWrapper<Example> exampleQueryWrapper=new LambdaQueryWrapper<Example>().eq(Example::getPid,pid).eq(Example::getStatus,1);
        problemDto.setExamples(exampleService.list(exampleQueryWrapper));
        return problemDto;
    }

    @Override
    public ProblemDto infoIncludeAllExamples(String pid, String token) {
        return null;
    }

    @Override
    public List<ProblemTag> tags() {
        return tagService.list();
    }

    @SneakyThrows
    @Override
    public Page<ProblemTrueCode> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo, String token) {
        String username=UserUtil.getUsernameByToken(redisTemplate,token);
        Problem problem=this.getById(queryVo.getPid());
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权查看此题目正确代码");
        }
        Page<ProblemTrueCode> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<ProblemTrueCode> queryWrapper = new LambdaQueryWrapper<ProblemTrueCode>().eq(ProblemTrueCode::getPid,queryVo.getPid());
        return trueCodeService.getBaseMapper().selectPage(page,queryWrapper);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBaseInfo(ProblemBaseInfoUpdateVo updateVo,String token) {
        Problem problem = this.getById(updateVo.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        problem.setTitle(updateVo.getTitle());
        problem.setStatus(updateVo.getStatus());
        this.updateById(problem);
        LambdaQueryWrapper<ProblemTagRelation> queryWrapper=new LambdaQueryWrapper<ProblemTagRelation>().eq(ProblemTagRelation::getPid,updateVo.getPid());
        tagRelationService.getBaseMapper().delete(queryWrapper);
        List<ProblemTagRelation> tagRelations=new ArrayList<>();
        updateVo.getTags().forEach(tagId->{
            tagRelations.add(new ProblemTagRelation(null,updateVo.getPid(),tagId));
        });
        tagRelationService.saveBatch(tagRelations);
    }

    @SneakyThrows
    @Override
    public void updateProblemBody(ProblemBodyUpdateVo updateVo,String token) {
        Problem problem = this.getById(updateVo.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        BeanUtils.copyProperties(updateVo,problem);
        this.updateById(problem);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateProblemTrueCode(ProblemTrueCodeUpdateVo updateVo, String token) {
        Problem problem = this.getById(updateVo.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(problem.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        if(compilerService.compile(updateVo.getCode(), updateVo.getLanguage(),updateVo.getVersion())!= JudgeResult.Status.Accepted){
            throw  new CodeCompileException("编译未成功，操作失败",null,null);
        }
        ProblemTrueCode problemTrueCode=new ProblemTrueCode();
        problemTrueCode.setPid(updateVo.getPid());
        problemTrueCode.setCode(updateVo.getCode());
        problemTrueCode.setLanguage(updateVo.getLanguage().getName());
        problemTrueCode.setVersion(updateVo.getVersion());
        problemTrueCode.setCodeId(updateVo.getCodeId());
        problemTrueCodeService.saveOrUpdate(problemTrueCode);
    }

    @Override
    public void deleteTrueCode(String codeId) {
        problemTrueCodeService.removeById(codeId);
    }
}
