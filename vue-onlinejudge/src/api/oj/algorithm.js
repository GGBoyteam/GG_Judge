import request from '@/utils/request'

export function list(query){
  return request({
    url: '/judge-service/algorithm/list',
    method: 'get',
    params: query
  })
}

// 查询岗位列表
export function listProblem(query) {
  return request({
    url: '/judge-service/algorithm/listByToken',
    method: 'get',
    params: query
  })
}

export function listLimit(query){
  return request({
    url: 'judge-service/algorithm/compilerLimits',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getProblem(pid) {
  return request({
    url: '/judge-service/algorithm/info/' + pid,
    method: 'get'
  })
}

export function getProblemTrueCode(query) {
  return request({
    url: '/judge-service/algorithm/trueCodeListByToken',
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

export function testExample(data){
  return request({
    url: '/judge-service/algorithm/testExample',
    method: 'post',
    data: data
  })
}

export function saveAlgorithmExample(data){
  return request({
    url: '/judge-service/algorithmEdit/saveExample',
    method: 'post',
    data: data
  })
}

export function updateAlgorithmExample(data){
  return request({
    url: '/judge-service/algorithmEdit/updateExample',
    method: 'post',
    data: data
  })
}

export function deleteAlgorithmExample(data) {
  return request({
    url: '/judge-service/algorithmEdit/deleteExample',
    method: 'delete',
    data: data
  })
}

export function addCompileLimit(data){
  return request({
    url: '/judge-service/algorithmEdit/addCompileLimit',
    method: 'post',
    data: data
  })
}

export function updateCompileLimit(data){
  return request({
    url: '/judge-service/algorithmEdit/updateCompileLimit',
    method: 'post',
    data: data
  })
}

export function deleteCompileLimit(data){
  return request({
    url: '/judge-service/algorithmEdit/deleteCompileLimit',
    method: 'delete',
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
    url: '/judge-service/algorithm/tags',
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

export function updateAlgorithmBaseInfo(data) {
  return request({
    url: '/judge-service/algorithmEdit/updateBaseInfo',
    method: 'post',
    data: data
  })
}

export function updateAlgorithmBody(data) {
  return request({
    url: '/judge-service/algorithmEdit/updateBody',
    method: 'post',
    data: data
  })
}

export function updateAlgorithmTrueCode(data){
  return request({
    url: '/judge-service/algorithmEdit/updateTrueCode',
    method: 'post',
    data: data
  })
}

export function addAlgorithmTrueCode(data){
  return request({
    url: '/judge-service/algorithmEdit/addTrueCode',
    method: 'post',
    data: data
  })
}


export function saveOrUpdateProblemTrueCode(data) {
  return request({
    url: '/judge-service/algorithm/saveOrUpdateProblemTrueCode',
    method: 'post',
    data: data
  })
}
export function deleteTrueCode(id){
  return request({
    url: '/judge-service/algorithm/deleteTrueCode/'+id,
    method: 'delete'
  })
}

export function examples(data){
  return request({
    url: '/judge-service/algorithm/examples',
    method: 'get',
    params: data
  })
}



// 删除岗位
export function delPost(postId) {
  return request({
    url: '/system/post/' + postId,
    method: 'delete'
  })
}
