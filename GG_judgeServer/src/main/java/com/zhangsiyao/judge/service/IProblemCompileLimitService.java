package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.ProblemCompileLimit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dto.ProblemCompileLimitDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-22
 */
public interface IProblemCompileLimitService extends IService<ProblemCompileLimit> {
    IPage<ProblemCompileLimitDto> compilers(Long pid, Long pageNum, Long pageSize);

    void saveCompilerLimit(Long pid,String language,Long time,Long memory);

    void updateCompilerLimit(Long pid,Long id,String language,Long time,Long memory);

    void deleteCompilerLimit(Long[] ids);

}
