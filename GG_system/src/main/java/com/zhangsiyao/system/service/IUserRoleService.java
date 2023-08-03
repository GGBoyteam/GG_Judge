package com.zhangsiyao.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.service.dao.Role;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import com.zhangsiyao.common.entity.service.dao.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.service.dto.RoleDto;
import com.zhangsiyao.common.entity.service.vo.RoleUserQueryVo;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-08-02
 */
public interface IUserRoleService extends IService<UserRole> {

    List<RoleDto> getRoleByUserId(String id);

    void update(UserAddOrUpdateVo addOrUpdateVo);

    Page<UserInfo> listByRoleId( RoleUserQueryVo roleUserQueryVo);

    Page<Role> listByUserId(RoleUserQueryVo roleUserQueryVo);
}
