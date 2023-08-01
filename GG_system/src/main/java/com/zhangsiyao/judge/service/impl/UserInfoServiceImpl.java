package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.common.entity.auth.vo.UserLoginAddOrUpdate;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.service.dao.RolePermission;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import com.zhangsiyao.common.entity.service.dto.UserPermissionDto;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
import com.zhangsiyao.common.entity.service.vo.UserQueryVo;
import com.zhangsiyao.common.exception.RegisterException;
import com.zhangsiyao.common.service.feign.UserAuthService;
import com.zhangsiyao.judge.mapper.UserInfoMapper;
import com.zhangsiyao.judge.service.IRolePermissionService;
import com.zhangsiyao.judge.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
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

    @Autowired
    UserAuthService userAuthService;

    @Override
    public R<UserPermissionDto> getInfo(String token) {
        UserPermissionDto userPermissionDto =new UserPermissionDto();
        try {
            Map<String, Object> user = objectMapper.readValue(redisTemplate.opsForValue().get(token), new TypeReference<Map<String,Object>>() {});
            UserInfo one = this.query().eq("username", user.get("username")).one();
            userPermissionDto.setInfo(one);
            List<RolePermission> permissions = permissionService.query().eq("role_id", one.getRoleId()).list();
            userPermissionDto.setPermissions(permissions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return R.success(userPermissionDto);
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
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddOrUpdateVo addOrUpdateVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, addOrUpdateVo.getUsername());
        if(this.count(queryWrapper)!=0){
            throw new RegisterException("该账户经存在");
        }
        UserLoginAddOrUpdate passwordVo=new UserLoginAddOrUpdate();
        passwordVo.setUsername(addOrUpdateVo.getUsername());
        passwordVo.setPassword(addOrUpdateVo.getPassword());
        userAuthService.register(passwordVo);
        this.save(addOrUpdateVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String[] ids) {
       for(String id:ids){
           userAuthService.delete(this.getById(id).getUsername());
           this.getBaseMapper().deleteById(id);
       }
    }

    @Override
    public void update(UserAddOrUpdateVo addOrUpdateVo) {
        UserInfo info = this.getById(addOrUpdateVo.getId());
        if(info==null){
            return;
        }
        addOrUpdateVo.setUsername(info.getUsername());
        this.updateById(addOrUpdateVo);
    }

}
