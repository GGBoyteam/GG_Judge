package com.zhangsiyao.auth.service;

import com.zhangsiyao.auth.entity.dao.Userlogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.auth.entity.dto.AuthResultDto;
import com.zhangsiyao.auth.entity.vo.UserPasswordVo;
import com.zhangsiyao.common.result.R;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
public interface IUserloginService extends IService<Userlogin> {
    R<AuthResultDto> register(UserPasswordVo passwordVo);

    R<AuthResultDto> loginByPassword(UserPasswordVo passwordVo);

    Map<Object,Object> loadAllData(String username);
}
