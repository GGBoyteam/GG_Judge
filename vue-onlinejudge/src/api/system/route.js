import request from '@/utils/request'

// 查询菜单列表
export function listRoute(query) {
  return request({
    url: '/system-service/route/list',
    method: 'get',
    params: query
  })
}

export function listPermissions(){
  return request({
    url: '/system-service/permission/list',
    method: 'get',
  })
}

// 查询菜单详细
export function getRoute(id) {
  return request({
    url: '/system-service/route/info/' + id,
    method: 'get'
  })
}

// 查询菜单下拉树结构
export function treeSelect() {
  return request({
    url: '/system-service/route/getRouteTree',
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function roleRouteTreeSelect(roleId) {
  return request({
    url: '/system-service/role/selected/' + roleId,
    method: 'get'
  })
}

// 新增菜单
export function addRoute(data) {
  return request({
    url: '/system-service/route/addOrUpdate',
    method: 'post',
    data: data
  })
}

// 修改菜单
export function updateRoute(data) {
  return request({
    url: '/system-service/route/addOrUpdate',
    method: 'post',
    data: data
  })
}

// 删除菜单
export function delRoute(id) {
  return request({
    url: '/system-service/route/delete/' +id,
    method: 'delete'
  })
}


// 改变状态
export function changeStatus(id) {
  return request({
    url: '/system-service/route/changeStatus/' +id,
    method: 'post'
  })
}