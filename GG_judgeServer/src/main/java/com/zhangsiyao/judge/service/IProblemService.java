package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.vo.ProblemBaseInfoUpdateVo;
import com.zhangsiyao.common.entity.judge.vo.ProblemQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
public interface IProblemService extends IService<Problem> {

    Page<ProblemDto> list(ProblemQueryVo queryVo,String token);

    ProblemDto info(String pid);

    List<ProblemTag> tags();

    void updateBaseInfo(ProblemBaseInfoUpdateVo updateVo);

}
