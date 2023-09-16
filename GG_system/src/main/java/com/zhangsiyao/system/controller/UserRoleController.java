package com.zhangsiyao.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.system.dao.User;
import com.zhangsiyao.common.entity.system.dao.UserRole;
import com.zhangsiyao.common.entity.system.dto.RoleDto;
import com.zhangsiyao.common.entity.system.vo.RoleAllocateUserVo;
import com.zhangsiyao.common.entity.system.vo.UserAddOrUpdateVo;
import com.zhangsiyao.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;

    @GetMapping("/getRole/{userId}")
    public R<List<RoleDto>> getRoleByUserId(@PathVariable String userId){
        return R.success(userRoleService.getRoleByUserId(userId));
    }

    @PostMapping("/update")
    public R<String> update(@RequestBody UserAddOrUpdateVo addOrUpdateVo){
        userRoleService.update(addOrUpdateVo);
        return R.success();
    }

    @GetMapping("/allocatedUser")
    public R<Page<User>> allocatedUserList(RoleAllocateUserVo allocateUserVo){
        return R.success(userRoleService.allocateUserList(allocateUserVo));
    }

    @GetMapping("/unallocatedUser")
    public R<Page<User>> unallocatedUserList(RoleAllocateUserVo allocateUserVo){
        return R.success(userRoleService.unallocateUserList(allocateUserVo));
    }

    @PutMapping("/cancelAllocateUser")
    public R<String> cancel( String roleId,String userId){
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId,roleId).eq(UserRole::getUserId,userId);
        userRoleService.getBaseMapper().delete(queryWrapper);
        return R.success();
    }

}
