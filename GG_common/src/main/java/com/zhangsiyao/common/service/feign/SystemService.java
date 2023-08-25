package com.zhangsiyao.common.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("system-service")
public interface SystemService {

    @GetMapping("feign/user/permissions/{username}")
    List<String> permissions(@PathVariable(value = "username") String username);
}
