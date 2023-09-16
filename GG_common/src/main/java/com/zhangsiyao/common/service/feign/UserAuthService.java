package com.zhangsiyao.common.service.feign;

import com.zhangsiyao.common.entity.auth.dto.AuthResultDto;
import com.zhangsiyao.common.entity.auth.vo.AuthAddOrUpdate;
import com.zhangsiyao.common.entity.auth.vo.UserPasswordVo;
import com.zhangsiyao.common.entity.common.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("auth-service")
@Component
public interface UserAuthService {
    @PostMapping("/auth/addOrUpdate")
    R<AuthResultDto> register(AuthAddOrUpdate addOrUpdate);

    @DeleteMapping("/auth/delete/{id}")
    R<String> delete(@PathVariable("id") String id);

    @PostMapping("/auth/resetPwd")
    R<String> resetPwd(@RequestBody UserPasswordVo passwordVo);
}
