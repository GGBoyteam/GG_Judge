package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.Algorithm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTag;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTrueCode;
import com.zhangsiyao.common.entity.judge.dto.AlgorithmDto;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemAlgorithmExampleDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemSubmissionResultDto;
import com.zhangsiyao.common.entity.judge.vo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
public interface IAlgorithmService extends IService<Algorithm> {

    /**
     * 通过token查询作者出的题目列表
     * */
    Page<AlgorithmDto> listByToken(ProblemQueryVo queryVo, String token);

    /**
     * 分页查询所有启用的题目
     * */
    Page<AlgorithmDto> listAll(ProblemQueryVo queryVo);

    Page<AlgorithmDto> listByAuthor(ProblemQueryVo queryVo);

    /**
     * 查看问题信息(不包括判题样例)
     * */
    AlgorithmDto info(String pid);

    AlgorithmDto infoIncludeAllExamples(String pid, String token);

    List<AlgorithmTag> tags();

    Page<AlgorithmTrueCode> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo, String token);

    Page<ProblemAlgorithmExampleDto> examples(AlgorithmExampleQueryVo queryVo, String token);

    CodeCompileAndRunResultDto testExample(AlgorithmExampleTestVo testVo);

    void updateBaseInfo(AlgorithmBaseInfoUpdateVo updateVo, String token);

    void updateProblemBody(ProblemBodyUpdateVo updateVo,String token);


    void saveOrUpdateProblemTrueCode(ProblemTrueCodeUpdateVo updateVo, String token);


    ProblemSubmissionResultDto submission(ProblemSubmissionVo submissionVo);
    void deleteTrueCode(String codeId);
}
