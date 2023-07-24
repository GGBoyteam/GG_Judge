package com.zhangsiyao.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.admin.mapper.CompilerMapper;
import com.zhangsiyao.admin.service.CompilerService;
import com.zhangsiyao.common.entity.Compiler;
import org.springframework.stereotype.Service;

@Service
public class CompilerServiceImpl extends ServiceImpl<CompilerMapper, Compiler> implements CompilerService {
}
