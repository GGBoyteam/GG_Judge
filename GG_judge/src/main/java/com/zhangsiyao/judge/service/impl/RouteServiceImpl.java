package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.DynamicRouteDto;
import com.zhangsiyao.judge.entity.dto.RouteTreeNode;
import com.zhangsiyao.judge.entity.vo.RouteQueryVo;
import com.zhangsiyao.judge.mapper.RouteMapper;
import com.zhangsiyao.judge.service.IRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {

    @Override
    public List<DynamicRouteDto> getDynamicRoutes() {
        List<Route> all = this.list();
        List<DynamicRouteDto> allDto=new ArrayList<>();
        for(Route route:all){
            DynamicRouteDto dynamicRouteDto =new DynamicRouteDto();
            BeanUtils.copyProperties(route, dynamicRouteDto);
            BeanUtils.copyProperties(route, dynamicRouteDto.getMeta());
            allDto.add(dynamicRouteDto);
        }
        return allDto.stream()
                .filter((route -> route.getParent().equals(0L)))
                .peek(dynamicRouteDto -> dynamicRouteDto.setChildren(getChildren(dynamicRouteDto,allDto)))
                .collect(Collectors.toList());
    }


    private List<DynamicRouteDto> getChildren(DynamicRouteDto root, List<DynamicRouteDto> all){
        List<DynamicRouteDto> collect = all.stream()
                .filter(dynamicRouteDto -> dynamicRouteDto.getParent().equals(root.getId()))
                .peek(dynamicRouteDto -> dynamicRouteDto.setChildren(getChildren(dynamicRouteDto, all)))
                .collect(Collectors.toList());
        return collect.size()==0?null:collect;
    }

    @Override
    public List<Route> list(RouteQueryVo routeQueryVo) {
        QueryWrapper<Route> queryWrapper=new QueryWrapper<>();
        if(routeQueryVo!=null){
            if(!StringUtils.isEmpty(routeQueryVo.getName())){
                queryWrapper=queryWrapper.like("title",routeQueryVo.getName());
            }
            if(routeQueryVo.getStatus()!=null){
                queryWrapper=queryWrapper.eq("status",routeQueryVo.getStatus());
            }
        }

        return this.list(queryWrapper);
    }


    @Override
    public List<RouteTreeNode> getRouteTree(){
        List<Route> routes=this.list();
        List<RouteTreeNode> menus=new ArrayList<>();
        routes.forEach(route -> {
            RouteTreeNode node=new RouteTreeNode();
            node.setId(route.getId());
            node.setTitle(route.getTitle());
            node.setParent(route.getParent());
            menus.add(node);
        });
        return menus.stream()
                .filter(menu->menu.getParent()==0)
                .peek(menu->menu.setChildren(generateChildren(menu,menus)))
                .collect(Collectors.toList());
    }
    private List<RouteTreeNode> generateChildren(RouteTreeNode root,List<RouteTreeNode> menus){
        return menus.stream()
                .filter(menu->menu.getParent().equals(root.getId()))
                .peek(menu->menu.setChildren(generateChildren(menu,menus)))
                .collect(Collectors.toList());
    }

}
