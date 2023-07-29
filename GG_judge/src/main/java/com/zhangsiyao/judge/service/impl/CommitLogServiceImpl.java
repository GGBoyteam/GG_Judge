package com.zhangsiyao.judge.service.impl;

import com.zhangsiyao.judge.entity.dao.CommitLog;
import com.zhangsiyao.judge.mapper.CommitLogMapper;
import com.zhangsiyao.judge.service.ICommitLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class CommitLogServiceImpl extends ServiceImpl<CommitLogMapper, CommitLog> implements ICommitLogService {

}
