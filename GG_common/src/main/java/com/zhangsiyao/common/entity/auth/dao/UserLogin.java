package com.zhangsiyao.common.entity.auth.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userlogin")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "username", type = IdType.INPUT)
    private String username;

    private String password;

    private String email;

    private String qq;

    private String wx;

    private Boolean enable;


}
