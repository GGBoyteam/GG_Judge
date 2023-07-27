package com.zhangsiyao.auth.service.impl;

import com.zhangsiyao.auth.entity.dao.Role;
import com.zhangsiyao.auth.mapper.RoleMapper;
import com.zhangsiyao.auth.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
