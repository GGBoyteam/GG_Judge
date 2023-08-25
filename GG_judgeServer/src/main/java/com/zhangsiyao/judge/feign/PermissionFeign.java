package com.zhangsiyao.judge.feign;

import com.zhangsiyao.common.entity.common.dto.R;
import com.zhangsiyao.common.utils.PermissionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionFeign {

    @GetMapping("/list")
    public List<String> list(){
        return new ArrayList<>(PermissionUtil.getPermissions());
    }
}
