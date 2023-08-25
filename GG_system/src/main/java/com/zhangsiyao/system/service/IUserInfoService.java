package com.zhangsiyao.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.system.dao.UserInfo;
import com.zhangsiyao.common.entity.system.dto.UserInfoDto;
import com.zhangsiyao.common.entity.system.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.system.vo.UserQueryVo;

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
    R<UserPermissionDto> getInfo(String token);

    Page<UserInfo> list(UserQueryVo queryVo);

    void add(UserAddOrUpdateVo addOrUpdateVo);

    void delete(String[] id);

    void update(UserAddOrUpdateVo addOrUpdateVo);

    void resetPwd(UserPasswordVo userPasswordVo);

    UserInfoDto info(String id);

    List<String> permissions(String username);
}
