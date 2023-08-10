package com.zhangsiyao.common.entity.system.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Integer hidden;

    private Integer alwaysShow;

    private String redirect;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer status;

    private Long sort;

    private Integer noCache;

    private String title;

    private String icon;

    private Integer breadcrumb;

    private String activeMenu;

    private String link;

    private String routeType;


    @TableField(exist = false)
    private Integer isFrame;


}
