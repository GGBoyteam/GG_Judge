package com.zhangsiyao.judge.controller;

import com.zhangsiyao.common.result.R;
import com.zhangsiyao.judge.entity.dao.Route;
import com.zhangsiyao.judge.entity.dto.DynamicRouteDto;
import com.zhangsiyao.judge.entity.dto.RouteTreeNode;
import com.zhangsiyao.judge.entity.vo.RouteAddOrUpdateVo;
import com.zhangsiyao.judge.entity.vo.RouteQueryVo;
import com.zhangsiyao.judge.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/info/{id}")
    public R<Route> info(@PathVariable String id){
        Route route = routeService.getById(id);
        route.setIsFrame(StringUtils.isEmpty(route.getLink())?1:0);
        return R.success(route);
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody RouteAddOrUpdateVo updateVo){
        routeService.updateById(updateVo);
        return R.success();
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody RouteAddOrUpdateVo updateVo){
        routeService.addRoute(updateVo);
        return R.success();
    }

    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable String id){
        routeService.deleteRoute(id);
        return R.success();
    }



}
