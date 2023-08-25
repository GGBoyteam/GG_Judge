package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmCompileLimitDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-22
 */
public interface IAlgorithmCompileLimitService extends IService<AlgorithmCompileLimit> {
    IPage<AlgorithmCompileLimitDto> compilers(Long pid, Long pageNum, Long pageSize);

    void saveCompilerLimit(Long pid,String language,Long time,Long memory);

    void updateCompilerLimit(Long pid,Long id,String language,Long time,Long memory);

    void deleteCompilerLimit(Long[] ids);

}
