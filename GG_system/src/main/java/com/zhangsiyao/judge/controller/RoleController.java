package com.zhangsiyao.judge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.Role;
import com.zhangsiyao.judge.entity.dto.RolePermissionDto;
import com.zhangsiyao.judge.entity.vo.RoleQueryVo;
import com.zhangsiyao.judge.entity.vo.RoleAddOrUpdateVo;
import com.zhangsiyao.judge.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;
    @GetMapping("/list")
    public R<Page<Role>> list( RoleQueryVo queryVo){
        return R.success(roleService.list(queryVo));
    }

    @GetMapping("/info/{id}")
    public R<Role> get(@PathVariable String id){
        return R.success(roleService.getById(id));
    }

    @GetMapping("/selected/{id}")
    public R<RolePermissionDto> selected(@PathVariable String id){
        return R.success(roleService.selected(id));
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody RoleAddOrUpdateVo roleAddOrUpdateVo){
        roleService.update(roleAddOrUpdateVo);
        return R.success();
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody RoleAddOrUpdateVo roleAddOrUpdateVo){
        roleService.add(roleAddOrUpdateVo);
        return R.success();
    }

    @PutMapping("/changeStatus")
    public R<String> changeStatus(@RequestBody Map<String,Long> param){
        roleService.changeStatus(param.get("roleId"),param.get("status"));
        return R.success();
    }

    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable String id){
        roleService.delete(id);
        return R.success();
    }
}
