package com.zhangsiyao.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.service.dao.UserInfo;
import com.zhangsiyao.common.entity.service.dto.RoleDto;
import com.zhangsiyao.common.entity.service.vo.RoleUserQueryVo;
import com.zhangsiyao.common.entity.service.vo.UserAddOrUpdateVo;
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

    @GetMapping("/listByRoleId")
    public R<Page<UserInfo>> listByRoleId(RoleUserQueryVo roleUserQueryVo){
        return R.success(userRoleService.listByRoleId(roleUserQueryVo));
    }

}
