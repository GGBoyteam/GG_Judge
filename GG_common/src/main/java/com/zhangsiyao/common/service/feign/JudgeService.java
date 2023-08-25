package com.zhangsiyao.common.service.feign;

import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("judge-service")
public interface JudgeService {
    @GetMapping("/permission/list")
    List<String> permissionList();
}
