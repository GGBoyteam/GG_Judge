package com.zhangsiyao.auth.service;

import com.zhangsiyao.auth.entity.dao.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.auth.entity.dto.AuthResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.common.result.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
public interface IUserloginService extends IService<UserLogin> {
    R<AuthResultDto> register(UserPasswordVo passwordVo);

    R<AuthResultDto> loginByPassword(UserPasswordVo passwordVo);


    R<String> logout(String token);
}
