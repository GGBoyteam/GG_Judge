package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmCompileLimitDto;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmCompileLimitDeleteVo;

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

    void saveCompilerLimit(AlgorithmCompileLimit compileLimit,String token);

    void updateCompilerLimit(AlgorithmCompileLimit compileLimit,String token);

    void deleteCompilerLimit(AlgorithmCompileLimitDeleteVo deleteVo,String token);

}
