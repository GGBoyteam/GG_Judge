package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.judge.entity.dto.UserDto;
import com.zhangsiyao.judge.entity.dto.UserInfoDto;
import com.zhangsiyao.judge.entity.vo.UserQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
public interface IUserInfoService extends IService<UserInfo> {
    R<UserInfoDto> getInfo(String token);

    Page<UserInfo> list(UserQueryVo queryVo);
}
