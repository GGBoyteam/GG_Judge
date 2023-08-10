package com.zhangsiyao.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsiyao.common.entity.system.dao.Route;
import com.zhangsiyao.common.entity.system.dto.DynamicRouteDto;
import com.zhangsiyao.common.entity.system.dto.RouteTreeNode;
import com.zhangsiyao.common.entity.system.vo.RouteAddOrUpdateVo;
import com.zhangsiyao.common.entity.system.vo.RouteQueryVo;

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
