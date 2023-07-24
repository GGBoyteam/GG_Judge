package com.zhangsiyao.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.admin.mapper.CompileParamMapper;
import com.zhangsiyao.admin.service.CompileParamService;
import com.zhangsiyao.common.entity.CompileParam;
import org.springframework.stereotype.Service;

@Service
public class CompileParamServiceImpl extends ServiceImpl<CompileParamMapper, CompileParam> implements CompileParamService {
}
