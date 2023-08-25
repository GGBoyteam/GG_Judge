package com.zhangsiyao.system.controller;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.utils.PermissionUtil;
import com.zhangsiyao.system.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IRolePermissionService permissionService;

    @GetMapping("/list")
    public R<List<String>> list(){
        return R.success(permissionService.permissions());
    }
}
