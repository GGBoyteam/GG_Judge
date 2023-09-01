package com.zhangsiyao.judge.service;

import com.zhangsiyao.common.entity.judge.dao.AlgorithmTrueCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmTrueCodeSaveVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmTrueCodeUpdateVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-12
 */
public interface IAlgorithmTrueCodeService extends IService<AlgorithmTrueCode> {

    public void updateTrueCode(AlgorithmTrueCodeUpdateVo updateVo,String token);

    public void saveTrueCode(AlgorithmTrueCodeSaveVo saveVo,String token);

}
