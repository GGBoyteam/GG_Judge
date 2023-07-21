package com.zhangsiyao.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.admin.mapper.ProblemMapper;
import com.zhangsiyao.common.entity.Problem;
import com.zhangsiyao.admin.service.ProblemService;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
}
