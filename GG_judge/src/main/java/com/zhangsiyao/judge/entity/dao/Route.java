package com.zhangsiyao.judge.entity.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.DataTruncation;
import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("route")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路由主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 前端组件
     */
    private String component;

    /**
     * 父路由id
     */
    private Long parent;

    /**
     * 权限(#代表不限制权限)
     */
    private String permission;

    private Boolean hidden;

    private Boolean alwaysShow;

    private String redirect;

    private Date createDate;

    private Integer status;

    private Long sort;

    private Boolean noCache;

    private String title;

    private String icon;

    private Boolean breadcrumb;

    private String activeMenu;


}
