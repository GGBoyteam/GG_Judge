package com.zhangsiyao.judge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.DynamicRouteDto;
import com.zhangsiyao.judge.entity.dto.RouteTreeNode;
import com.zhangsiyao.judge.entity.vo.RouteAddOrUpdateVo;
import com.zhangsiyao.judge.entity.vo.RouteQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangsiyao
 * @since 2023-07-28
 */
public interface IRouteService extends IService<Route> {
    List<DynamicRouteDto> getDynamicRoutes();

    List<Route> list(RouteQueryVo routeQueryVo);

    List<RouteTreeNode> getRouteTree();


    void addOrUpdateRoute(RouteAddOrUpdateVo routeAddOrUpdateVo);

    void deleteRoute(String id);

    void changeStatus(String id);


}
