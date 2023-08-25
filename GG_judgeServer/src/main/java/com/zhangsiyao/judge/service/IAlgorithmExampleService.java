package com.zhangsiyao.judge.service;

import com.zhangsiyao.common.entity.judge.dao.AlgorithmExample;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
public interface IAlgorithmExampleService extends IService<AlgorithmExample> {
    void saveExample(AlgorithmExample algorithmExample, String token);

    void updateExample(AlgorithmExample algorithmExample, String token);

    void deleteExample(Long pid, List<Long> ids, String token);
}
