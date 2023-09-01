package com.zhangsiyao.judge.service.impl;

import com.zhangsiyao.common.entity.judge.dao.Algorithm;
import com.zhangsiyao.common.entity.judge.dao.AlgorithmTrueCode;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmTrueCodeSaveVo;
import com.zhangsiyao.common.entity.judge.vo.AlgorithmTrueCodeUpdateVo;
import com.zhangsiyao.common.utils.UserUtil;
import com.zhangsiyao.judge.compiler.JudgeResult;
import com.zhangsiyao.judge.exception.CodeCompileException;
import com.zhangsiyao.judge.exception.NotProblemAuthorException;
import com.zhangsiyao.judge.mapper.AlgorithmTrueCodeMapper;
import com.zhangsiyao.judge.service.IAlgorithmService;
import com.zhangsiyao.judge.service.IAlgorithmTrueCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsiyao.judge.service.ICompilerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-08-12
 */
@Service
public class AlgorithmTrueCodeServiceImpl extends ServiceImpl<AlgorithmTrueCodeMapper, AlgorithmTrueCode> implements IAlgorithmTrueCodeService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IAlgorithmService algorithmService;

    @Autowired
    ICompilerService compilerService;

    @SneakyThrows
    @Override
    public void updateTrueCode(AlgorithmTrueCodeUpdateVo updateVo, String token) {
        Algorithm algorithm = algorithmService.getById(updateVo.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        if(compilerService.compile(updateVo.getCode(), updateVo.getLanguage(),updateVo.getVersion())!= JudgeResult.Status.Accepted){
            throw  new CodeCompileException("编译未成功，操作失败",null,null);
        }
        this.updateById(updateVo);
    }

    @SneakyThrows
    @Override
    public void saveTrueCode(AlgorithmTrueCodeSaveVo saveVo, String token) {
        Algorithm algorithm = algorithmService.getById(saveVo.getPid());
        String username = UserUtil.getUsernameByToken(redisTemplate, token);
        if(!username.equals(algorithm.getAuthor())){
            throw new NotProblemAuthorException("您不是此题作者，无权修改此题目");
        }
        if(compilerService.compile(saveVo.getCode(), saveVo.getLanguage(),saveVo.getVersion())!= JudgeResult.Status.Accepted){
            throw  new CodeCompileException("编译未成功，操作失败",null,null);
        }
        this.save(saveVo);
    }
}
