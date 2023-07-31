package com.zhangsiyao.gateway.service;


import com.zhangsiyao.common.entity.common.dto.R;

import java.io.IOException;

/**
 * 验证码处理
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    R createCaptcha() throws IOException;

    /**
     * 校验验证码
     */
    void checkCaptcha(String key, String value);
}
