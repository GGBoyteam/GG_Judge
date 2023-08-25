package com.zhangsiyao.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.entity.system.dao.Role;
import com.zhangsiyao.common.entity.system.dto.RolePermissionDto;
import com.zhangsiyao.common.entity.system.vo.RoleQueryVo;
import com.zhangsiyao.common.entity.system.vo.RoleAddOrUpdateVo;
import com.zhangsiyao.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @PreAuthorize("hasAnyAuthority('system:role:info','*:*:*')")
    @GetMapping("/list")
    public R<Page<Role>> list( RoleQueryVo queryVo){
        return R.success(roleService.list(queryVo));
    }

    @PreAuthorize("hasAnyAuthority('system:role:info','*:*:*')")
    @GetMapping("/info/{id}")
    public R<Role> get(@PathVariable String id){
        return R.success(roleService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('system:role:info','*:*:*')")
    @GetMapping("/selected/{id}")
    public R<RolePermissionDto> selected(@PathVariable String id){
        return R.success(roleService.selected(id));
    }

    @PreAuthorize("hasAnyAuthority('system:role:update','*:*:*')")
    @PutMapping("/update")
    public R<String> update(@RequestBody RoleAddOrUpdateVo roleAddOrUpdateVo){
        roleService.update(roleAddOrUpdateVo);
        return R.success();
    }

    @PreAuthorize("hasAnyAuthority('system:role:update','*:*:*')")
    @PostMapping("/add")
    public R<String> add(@RequestBody RoleAddOrUpdateVo roleAddOrUpdateVo){
        roleService.add(roleAddOrUpdateVo);
        return R.success();
    }

    @PreAuthorize("hasAnyAuthority('system:role:update','*:*:*')")
    @PutMapping("/changeStatus")
    public R<String> changeStatus(@RequestBody Map<String,Long> param){
        roleService.changeStatus(param.get("roleId"),param.get("status"));
        return R.success();
    }

    @PreAuthorize("hasAnyAuthority('system:role:delete','*:*:*')")
    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable String[] id){
        roleService.delete(id);
        return R.success();
    }
}
