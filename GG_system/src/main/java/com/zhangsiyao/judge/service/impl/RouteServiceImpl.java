package com.zhangsiyao.judge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.RolePermission;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.DynamicRouteDto;
import com.zhangsiyao.judge.entity.dto.RouteTreeNode;
import com.zhangsiyao.judge.entity.vo.RouteAddOrUpdateVo;
import com.zhangsiyao.judge.entity.vo.RouteQueryVo;
import com.zhangsiyao.judge.mapper.RouteMapper;
import com.zhangsiyao.judge.service.IRolePermissionService;
import com.zhangsiyao.judge.service.IRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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


    @Autowired
    IRolePermissionService rolePermissionService;

    @Override
    public List<DynamicRouteDto> getDynamicRoutes() {
        LambdaQueryWrapper<Route> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Route::getRouteType,"M").or().eq(Route::getRouteType,"C");
        List<Route> all = this.getBaseMapper().selectList(queryWrapper);
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


    @Override
    @Transactional(rollbackFor = {java.lang.Exception.class})
    public void addOrUpdateRoute(RouteAddOrUpdateVo routeAddOrUpdateVo) {
        if(routeAddOrUpdateVo.getParent()==0L&&!routeAddOrUpdateVo.getPath().startsWith("/")){
            routeAddOrUpdateVo.setPath("/"+routeAddOrUpdateVo.getPath());
        }
        if("M".equals(routeAddOrUpdateVo.getRouteType())){
            routeAddOrUpdateVo.setComponent("Layout");
            routeAddOrUpdateVo.setAlwaysShow(0);
            routeAddOrUpdateVo.setRedirect("noRedirect");
        }else if("F".equals(routeAddOrUpdateVo.getRouteType())){
            routeAddOrUpdateVo.setComponent(null);
            routeAddOrUpdateVo.setPath(null);
        }
        if(routeAddOrUpdateVo.getIsFrame()==0){
            routeAddOrUpdateVo.setLink(routeAddOrUpdateVo.getPath());
        }
        this.saveOrUpdate(routeAddOrUpdateVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoute(String id) {
        this.getBaseMapper().deleteById(id);

        LambdaQueryWrapper<Route> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper=queryWrapper.eq(Route::getParent,id);
        List<Route> routes = this.getBaseMapper().selectList(queryWrapper);
        Queue<Route> queue=new LinkedList<>(routes);
        while (!queue.isEmpty()){
            Route route=queue.poll();
            this.getBaseMapper().deleteById(route);
            queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper=queryWrapper.eq(Route::getParent,route.getId());
            List<Route> r = this.getBaseMapper().selectList(queryWrapper);
            queue.addAll(r);
        }

        LambdaQueryWrapper<RolePermission> rolePermissionQuery=new LambdaQueryWrapper<>();
        rolePermissionQuery=rolePermissionQuery.eq(RolePermission::getRouteId,id);
        rolePermissionService.getBaseMapper().delete(rolePermissionQuery);
    }

    @Override
    public void changeStatus(String id) {
        Route route = this.getById(id);
        route.setStatus(1-route.getStatus());
        this.updateById(route);
    }

}
