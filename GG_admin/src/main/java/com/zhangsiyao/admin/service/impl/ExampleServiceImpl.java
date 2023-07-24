package com.zhangsiyao.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.admin.mapper.ExampleMapper;
import com.zhangsiyao.admin.service.ExampleService;
import com.zhangsiyao.common.entity.Example;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements ExampleService {
}
