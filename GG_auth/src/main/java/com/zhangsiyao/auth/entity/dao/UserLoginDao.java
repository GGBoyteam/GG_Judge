package com.zhangsiyao.auth.entity.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("userlogin")
public class UserLoginDao implements Serializable {

    @TableId
    private long id;

    private String username;

    private String password;

    private String email;

    private String qq;

    private String wx;

    private long roleId;

}
