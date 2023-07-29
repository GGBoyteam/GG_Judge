package com.zhangsiyao.judge.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DynamicRouteDto implements Serializable {
    private Long id;
    private String name;
    private String path;
    private String component;
    private Long parent;
    private Boolean hidden;
    private Boolean alwaysShow;
    private String redirect;
    private List<String> permissions;
    private List<DynamicRouteDto> children;

    private Meta meta=new Meta();

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class Meta implements Serializable{
        private Boolean noCache;                  // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
        private String title;                  // 设置该路由在侧边栏和面包屑中展示的名字
        private String icon;           // 设置该路由的图标，对应路径src/assets/icons/svg
        private Boolean breadcrumb;             // 如果设置为false，则不会在breadcrumb面包屑中显示
        private String activeMenu;
    }
}
