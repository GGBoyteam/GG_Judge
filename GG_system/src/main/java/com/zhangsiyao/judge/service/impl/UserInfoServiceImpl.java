package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.judge.entity.dao.RolePermission;
import com.zhangsiyao.judge.entity.dao.UserInfo;
import com.zhangsiyao.judge.entity.dto.UserInfoDto;
import com.zhangsiyao.judge.entity.vo.UserAddOrUpdateVo;
import com.zhangsiyao.judge.entity.vo.UserQueryVo;
import com.zhangsiyao.judge.mapper.UserInfoMapper;
import com.zhangsiyao.judge.service.IRolePermissionService;
import com.zhangsiyao.judge.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IRolePermissionService permissionService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public R<UserInfoDto> getInfo(String token) {
        UserInfoDto userInfoDto=new UserInfoDto();
        try {
            Map<String, Object> user = objectMapper.readValue(redisTemplate.opsForValue().get(token), new TypeReference<Map<String,Object>>() {});
            UserInfo one = this.query().eq("username", user.get("username")).one();
            userInfoDto.setInfo(one);
            List<RolePermission> permissions = permissionService.query().eq("role_id", one.getRoleId()).list();
            userInfoDto.setPermissions(permissions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return R.success(userInfoDto);
    }

    @Override
    public Page<UserInfo> list(UserQueryVo queryVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper=new LambdaQueryWrapper<>();
        Page<UserInfo> page=Page.of(queryVo.getPageNum(), queryVo.getPageSize());
        if(!StringUtils.isEmpty(queryVo.getPhone())){
            queryWrapper=queryWrapper.like(UserInfo::getPhone,queryVo.getPhone());
        }
        if(!StringUtils.isEmpty(queryVo.getUsername())){
            queryWrapper=queryWrapper.like(UserInfo::getUsername,queryVo.getUsername());
        }
        if(queryVo.getStatus()!=null){
            queryWrapper=queryWrapper.eq(UserInfo::getStatus,queryVo.getStatus());
        }
        return this.getBaseMapper().selectPage(page,queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(UserAddOrUpdateVo addOrUpdateVo) {
        this.saveOrUpdate(addOrUpdateVo);
    }
}
