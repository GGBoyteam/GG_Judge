package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.dao.UserLogin;
import com.zhangsiyao.common.entity.judge.dao.Example;
import com.zhangsiyao.common.entity.judge.dao.Problem;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dao.ProblemTagRelation;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.vo.ProblemBaseInfoUpdateVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemBodyUpdateVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemQueryVo;
import com.zhangsiyao.judge.mapper.ProblemMapper;
import com.zhangsiyao.judge.service.IExampleService;
import com.zhangsiyao.judge.service.IProblemService;
import com.zhangsiyao.judge.service.IProblemTagRelationService;
import com.zhangsiyao.judge.service.IProblemTagService;
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
    IProblemTagRelationService tagRelationService;

    @SneakyThrows
    @Override
    public Page<ProblemDto> list(ProblemQueryVo queryVo,String token) {
        UserLogin userLogin=objectMapper.readValue(redisTemplate.opsForValue().get(token),UserLogin.class);
        Page<Problem> page=Page.of(queryVo.getPageNum(),queryVo.getPageSize());
        LambdaQueryWrapper<Problem> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Problem::getAuthor,userLogin.getUsername());
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
    public List<ProblemTag> tags() {
        return tagService.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBaseInfo(ProblemBaseInfoUpdateVo updateVo) {
        Problem problem = this.getById(updateVo.getPid());
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

    @Override
    public void updateProblemBody(ProblemBodyUpdateVo updateVo) {
        Problem problem = this.getById(updateVo.getPid());
        BeanUtils.copyProperties(updateVo,problem);
        this.updateById(problem);
    }
}
