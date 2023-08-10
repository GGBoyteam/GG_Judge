import request from '@/utils/request'

// 查询岗位列表
export function listProblem(query) {
  return request({
    url: '/judge-service/problem/list',
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

// 修改岗位
export function updateProblemBaseInfo(data) {
  return request({
    url: '/judge-service/problem/updateProblemBaseInfo',
    method: 'post',
    data: data
  })
}

// 删除岗位
export function delPost(postId) {
  return request({
    url: '/system/post/' + postId,
    method: 'delete'
  })
}
