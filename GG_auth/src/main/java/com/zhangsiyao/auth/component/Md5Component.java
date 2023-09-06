package com.zhangsiyao.auth.component;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class Md5Component {

    @Value("${md5.salt:zhangsiyao}")
    private String SALT;

    public String md5Hex(String origin){
        return DigestUtils.md5Hex(SALT+origin+SALT);
    }
}
