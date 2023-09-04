package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmCompileLimit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmCompileLimitDeleteVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmCompileLimitQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-22
 */
public interface IAlgorithmCompileLimitService extends IService<AlgorithmCompileLimit> {
    IPage compileLimitList(AlgorithmCompileLimitQueryVo queryVo,String token);

    void saveCompilerLimit(AlgorithmCompileLimit compileLimit,String token);

    void updateCompilerLimit(AlgorithmCompileLimit compileLimit,String token);

    void deleteCompilerLimit(AlgorithmCompileLimitDeleteVo deleteVo,String token);

}
