package com.zhangsiyao.auth.service;

import com.zhangsiyao.common.entity.auth.dao.Auth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.auth.dto.AuthResultDto;
import com.zhangsiyao.common.entity.auth.vo.AuthAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
public interface IAuthService extends IService<Auth> {
    void register(UserPasswordVo passwordVo);

    AuthResultDto loginByPassword(UserPasswordVo passwordVo) ;

    void logout(String token);

    void addOrUpdate(AuthAddOrUpdate addOrUpdate);

    void delete(String id);

    void resetPwd(UserPasswordVo passwordVo);
}
