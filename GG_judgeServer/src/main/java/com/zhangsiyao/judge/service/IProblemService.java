package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.judge.dao.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.judge.dao.ProblemTag;
import com.zhangsiyao.common.entity.judge.dao.ProblemTrueCode;
import com.zhangsiyao.common.entity.judge.dto.CodeCompileAndRunResultDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemExampleDto;
import com.zhangsiyao.common.entity.judge.dto.ProblemSubmissionResultDto;
import com.zhangsiyao.common.entity.judge.vo.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Stack;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-09
 */
public interface IProblemService extends IService<Problem> {



    /**
     * 通过token查询作者出的题目列表
     * */
    Page<ProblemDto> listByToken(ProblemQueryVo queryVo,String token);

    /**
     * 分页查询所有启用的题目
     * */
    Page<ProblemDto> listAll(ProblemQueryVo queryVo);

    Page<ProblemDto> listByAuthor(ProblemQueryVo queryVo);

    /**
     * 查看问题信息(不包括判题样例)
     * */
    ProblemDto info(String pid);

    ProblemDto infoIncludeAllExamples(String pid,String token);

    List<ProblemTag> tags();

    Page<ProblemTrueCode> trueCodeListByToken(ProblemTrueCodeQueryVo queryVo,String token);

    Page<ProblemExampleDto> examples(ProblemExampleQueryVo queryVo,String token);

    CodeCompileAndRunResultDto testExample(ProblemExampleTestVo testVo);

    void updateBaseInfo(ProblemBaseInfoUpdateVo updateVo, String token);

    void updateProblemBody(ProblemBodyUpdateVo updateVo,String token);


    void saveOrUpdateProblemTrueCode(ProblemTrueCodeUpdateVo updateVo, String token);

    void saveOrUpdateProblemExample(ProblemExampleUpdateVo updateVo,String token);

    ProblemSubmissionResultDto submission(ProblemSubmissionVo submissionVo);
    void deleteTrueCode(String codeId);
}
