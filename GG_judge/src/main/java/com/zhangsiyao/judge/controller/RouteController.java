package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.DynamicRouteDto;
import com.zhangsiyao.judge.entity.dto.RouteTreeNode;
import com.zhangsiyao.judge.entity.vo.RouteQueryVo;
import com.zhangsiyao.judge.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    IRouteService routeService;

    @GetMapping("/getRoute")
    public R<List<DynamicRouteDto>> getRoute(){
        return R.success(routeService.getDynamicRoutes());
    }

    @GetMapping("/getRouteTree")
    public R<List<RouteTreeNode>> getRouteTree(){
        return R.success(routeService.getRouteTree());
    }

    @GetMapping("/list")
    public R<List<Route>> list(RouteQueryVo queryVo){
        return R.success(routeService.list(queryVo));
    }
}
