import request from '@/utils/request'

// 查询岗位列表
export function listProblem(query) {
  return request({
    url: '/judge-service/problem/listByToken',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getProblem(pid) {
  return request({
    url: '/judge-service/problem/info/' + pid,
    method: 'get'
  })
}

export function getProblemTrueCode(query) {
  return request({
    url: '/judge-service/problem/trueCodeListByToken',
    method: 'get',
    params: query
  })
}

export function compileAndRun(data){
  return request({
    url: '/judge-service/compiler/compileRun',
    method: 'post',
    data: data
  })
}

export function compiler(){
  return request({
    url: '/judge-service/compiler/list',
    method: 'get',
  })
}

export function getTags() {
  return request({
    url: '/judge-service/problem/tags',
    method: 'get'
  })
}

// 新增岗位
export function addPost(data) {
  return request({
    url: '/system/post',
    method: 'post',
    data: data
  })
}

export function updateProblemBaseInfo(data) {
  return request({
    url: '/judge-service/problem/updateProblemBaseInfo',
    method: 'post',
    data: data
  })
}

export function updateProblemBody(data) {
  return request({
    url: '/judge-service/problem/updateProblemBody',
    method: 'post',
    data: data
  })
}


export function saveOrUpdateProblemTrueCode(data) {
  return request({
    url: '/judge-service/problem/saveOrUpdateProblemTrueCode',
    method: 'post',
    data: data
  })
}
export function test(){
  return request({
    url: '/judge-service/compiler/run',
    method: 'post'
  })
}


// 删除岗位
export function delPost(postId) {
  return request({
    url: '/system/post/' + postId,
    method: 'delete'
  })
}
