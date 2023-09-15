package com.zhangsiyao.common.entity.auth.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("auth")
public class Auth implements Serializable {

    /**用户名*/
    String username;

    /**密码*/
    String password;

    /**QQ账号*/
    Long qq;

    /**微信*/
    String wx;

    /**邮箱*/
    String email;

}
